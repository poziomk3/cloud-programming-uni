# Use the official Node.js LTS image as a base
FROM node:18

# Set the working directory inside the container
WORKDIR /app

# Copy package.json and package-lock.json first (for caching dependencies)
COPY package.json package-lock.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application files
COPY . .

# Set the default command (entrypoint.js will determine the role: publisher or consumer)
CMD ["node", "app.js"]
