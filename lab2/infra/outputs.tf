output "db_connection_strings" {
  value = {
    for key, db in aws_db_instance.services :
    key => "jdbc:postgresql://${db.endpoint}:${db.port}/${db.db_name}"
  }
  sensitive = true
}



output "s3_connection" {
  value = {
    bucket_name = aws_s3_bucket.minio_like_bucket.bucket
    endpoint    = "https://s3.amazonaws.com"
    note        = "Use your own AWS credentials to access the bucket"
  }
}
