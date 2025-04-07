provider "aws" {
  region = var.aws_region
}

data "aws_vpc" "default" {
  default = true
}

data "aws_subnets" "default" {
  filter {
    name   = "vpc-id"
    values = [data.aws_vpc.default.id]
  }
}

resource "aws_security_group" "db_sg" {
  name   = "lab2-db-sg"
  vpc_id = data.aws_vpc.default.id

  ingress {
    from_port   = 5432
    to_port     = 5432
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 5672
    to_port     = 5672
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_db_subnet_group" "default" {
  name       = "lab2-subnet-group"
  subnet_ids = data.aws_subnets.default.ids
}

resource "aws_db_instance" "services" {
  for_each               = var.dbs
  identifier             = "${replace(each.key, "_", "-")}-instance"
  allocated_storage      = 20
  engine                 = "postgres"
  engine_version         = "15"
  instance_class         = "db.t3.micro"
  username               = var.db_user
  password               = var.db_password
  db_name                = each.value
  db_subnet_group_name   = aws_db_subnet_group.default.name
  vpc_security_group_ids = [aws_security_group.db_sg.id]
  skip_final_snapshot    = true
  publicly_accessible    = true
}




resource "random_id" "bucket_id" {
  byte_length = 4
}

resource "aws_s3_bucket" "minio_like_bucket" {
  bucket = "lab2-bucket-${random_id.bucket_id.hex}"
}
