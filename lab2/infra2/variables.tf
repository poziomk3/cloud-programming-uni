variable "aws_region" {
  default = "us-east-1"
}

variable "dbs" {
  type = map(string)
  default = {
    user_service     = ""
    user_man_service = ""
    stats_service    = ""
    role_service     = ""
  }
}


variable "ecr_repo_url" {
  default = ""
}

variable "db_user" {
  default = ""
}

variable "db_password" {
  default = ""
}

variable "db_urls" {
  type = map(string)
  default = {
    user_service     = ""
    user_service     = ""
    user_man_service = ""
    stats_service    = ""
    role_service     = ""
  }
}

variable "rabbitmq_host" { default = "" }
variable "rabbitmq_user" { default = "" }
variable "rabbitmq_password" { default = "" }
variable "rabbitmq_vhost" { default = "" }

variable "mail_host" { default = "" }
variable "mail_port" { default = "" }
variable "mail_user" { default = "" }
variable "mail_password" { default = "" }

variable "minio_accesskey" { default = "" }
variable "minio_secretkey" { default = "" }
variable "minio_sessiontoken" { default = "" }
variable "minio_bucketname" { default = "" }
