variable "aws_region" {
  default = "us-east-1"
}

variable "db_user" {
  default = "adminuser"
}

variable "db_password" {
  default = "Y3ougOsIM1X4V8XcSKHE"
}

variable "dbs" {
  type = map(string)
  default = {
    user_service     = "userdb"
    user_man_service = "usermandb"
    stats_service    = "statsdb"
    role_service     = "roledb"
  }
}


