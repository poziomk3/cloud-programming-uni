resource "aws_ecs_task_definition" "tasks" {
  for_each                 = local.services
  family                   = each.key
  requires_compatibilities = ["FARGATE"]
  cpu                      = "256"
  memory                   = "512"
  network_mode             = "awsvpc"
  execution_role_arn       = data.aws_iam_role.ecs_task_execution_role.arn

  container_definitions = jsonencode([
    {
      name  = each.key
      image = "${var.ecr_repo_url}:lab2-${each.key}"

      portMappings = [
        {
          containerPort = 8080
          hostPort      = 8080
        }
      ]
      environment = [
        { name = "SPRING_DATASOURCE_URL",        value = each.value.db_url },
        { name = "SPRING_DATASOURCE_USERNAME",   value = var.db_user },
        { name = "SPRING_DATASOURCE_PASSWORD",   value = var.db_password },
        { name = "SPRING_RABBITMQ_HOST",         value = var.rabbitmq_host },
        { name = "SPRING_RABBITMQ_PORT",         value = "5671" },
        { name = "SPRING_RABBITMQ_USERNAME",     value = var.rabbitmq_user },
        { name = "SPRING_RABBITMQ_PASSWORD",     value = var.rabbitmq_password },
        { name = "SPRING_RABBITMQ_VIRTUAL_HOST", value = var.rabbitmq_vhost },
        { name = "SPRING_RABBITMQ_SSL_ENABLED",  value = "true" },
        { name = "SPRING_MAIL_HOST",             value = var.mail_host },
        { name = "SPRING_MAIL_PORT",             value = var.mail_port },
        { name = "SPRING_MAIL_USERNAME",         value = var.mail_user },
        { name = "SPRING_MAIL_PASSWORD",         value = var.mail_password },
        { name = "SPRING_PROFILES_ACTIVE",       value = "prod" },
        { name = "MINIO_URL",                    value = "https://s3.amazonaws.com" },
        { name = "MINIO_BUCKETNAME",             value = var.minio_bucketname },
        { name = "MINIO_ACCESSKEY",              value = var.minio_accesskey },
        { name = "MINIO_SECRETKEY",              value = var.minio_secretkey },
        { name = "MINIO_SESSIONTOKEN",           value = var.minio_sessiontoken }
      ]
       logConfiguration = {
      logDriver = "awslogs"
      options = {
        awslogs-group         = "/ecs/${each.key}"
        awslogs-region        = var.aws_region
        awslogs-stream-prefix = "ecs"
      }
    }
    }
  ])
}


resource "aws_ecs_service" "services" {
  for_each        = local.services
  name            = each.key
  cluster         = aws_ecs_cluster.lab2_cluster.id
  task_definition = aws_ecs_task_definition.tasks[each.key].arn
  desired_count   = 1
  launch_type     = "FARGATE"

  network_configuration {
    subnets          = data.aws_subnets.default.ids
    security_groups  = [aws_security_group.ecs_sg.id]
    assign_public_ip = true
  }

  load_balancer {
    target_group_arn = aws_lb_target_group.service_targets[each.key].arn
    container_name   = each.key
    container_port   = 8080
  }

  depends_on = [aws_lb_listener.http]
}


resource "aws_cloudwatch_log_group" "ecs_logs" {
  for_each = local.services
  name     = "/ecs/${each.key}"
  retention_in_days = 7
}

