locals {
  services = {
    user-service = {
      path   = "/user*"
      db_url = var.db_urls["user_service"]
    }
    user-man-service = {
      path   = "/user-man*"
      db_url = var.db_urls["user_man_service"]
    }
    stats-service = {
      path   = "/stats*"
      db_url = var.db_urls["stats_service"]
    }
    role-service = {
      path   = "/role*"
      db_url = var.db_urls["role_service"]
    }
    notification-service = {
      path   = "/notification*"
      db_url = "" # no DB
    }
  }
}
