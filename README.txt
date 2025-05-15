# Build Docker image
docker build -t consent-api .

# Run it
docker run -p 8889:8080 consent-api


# Open http://localhost:8899/consent

