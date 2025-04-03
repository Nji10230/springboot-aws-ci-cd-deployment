# Use Amazon Corretto 17 instead of OpenJDK to avoid rate limits
FROM public.ecr.aws/amazoncorretto/amazoncorretto:17

# Set the working directory inside the container
WORKDIR /app

# Copy the built application JAR file into the container
COPY ./target/SpringbootDataJpaRelationships.jar /app/app.jar

# Expose the application port (default is 8080)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
