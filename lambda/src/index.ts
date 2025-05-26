// npx tsc                      # Compile TypeScript to dist/
// cp package.json dist/
// cd dist
// npm install --only=prod
// zip -r lambda.zip .
import { S3 } from "aws-sdk";
//  @ts-ignore
import QRCode from "qrcode";
import { v4 as uuidv4 } from "uuid";

const BUCKET_NAME = "bk";
const BUCKET_REGION = "us-east-1";

export const handler = async (event: any) => {
  if (!event.url) {
    return {
      statusCode: 400,
      error: "Input does not contain a 'url' property.",
    };
  }
  let qrBuffer: Buffer;
  try {
    qrBuffer = await QRCode.toBuffer(event.url);
  } catch (err: any) {
    return {
      statusCode: 500,
      error: "QR code generation failed.",
      details: err.message,
    };
  }
  const filename = `qr-codes/${uuidv4()}.png`;
  const s3 = new S3({ region: BUCKET_REGION });

  try {
    await s3
      .putObject({
        Bucket: BUCKET_NAME,
        Key: filename,
        Body: qrBuffer,
        ContentType: "image/png",
      })
      .promise();
  } catch (err: any) {
    return {
      statusCode: 500,
      error: "S3 upload failed.",
      details: err.message,
    };
  }

  const temporaryUrl = await s3.getSignedUrlPromise("getObject", {
    Bucket: BUCKET_NAME,
    Key: filename,
    Expires: 3600,
  });
  const url = `https://${BUCKET_NAME}.s3.${BUCKET_REGION}.amazonaws.com/${filename}`;
  return {
    statusCode: 200,
    url,
    temporaryUrl,
  };
};
