# Deployment Guide for Restful Web Services

## Docker Setup

### Build Docker Image
```bash
docker build -t restfulwebservices .
```

### Run Docker Container Locally
```bash
docker run -p 8085:8085 \
  -e SPRING_DATASOURCE_URL=your_database_url \
  -e SPRING_DATASOURCE_USERNAME=your_username \
  -e SPRING_DATASOURCE_PASSWORD=your_password \
  restfulwebservices
```

## Render Deployment

### Option 1: Using render.yaml (Recommended)

1. **Push your code to GitHub/GitLab**
   ```bash
   git add .
   git commit -m "Add Docker and Render configuration"
   git push origin main
   ```

2. **Connect your repository to Render**
   - Go to [Render Dashboard](https://dashboard.render.com/)
   - Click "New +" → "Web Service"
   - Connect your GitHub/GitLab repository
   - Render will automatically detect the `render.yaml` file

3. **Configure Environment Variables**
   - Set `SPRING_PROFILES_ACTIVE=prod`
   - Configure database connection variables:
     - `DATABASE_URL` (your MySQL/PostgreSQL connection string)
     - `DATABASE_USER` (database username)
     - `DATABASE_PASSWORD` (database password)

4. **Deploy**
   - Render will automatically build and deploy your application

### Option 2: Manual Render Setup

1. **Create Web Service**
   - Go to Render Dashboard → "New +" → "Web Service"
   - Connect your repository
   - Environment: Docker
   - Docker Context: `.`
   - Dockerfile Path: `./Dockerfile`

2. **Configure Service**
   - Name: `restfulwebservices`
   - Plan: Free or Starter
   - Health Check Path: `/actuator/health`
   - Port: 8085

3. **Environment Variables**
   ```
   SPRING_PROFILES_ACTIVE=prod
   PORT=8085
   SPRING_DATASOURCE_URL=your_database_connection_string
   SPRING_DATASOURCE_USERNAME=your_db_username
   SPRING_DATASOURCE_PASSWORD=your_db_password
   SPRING_JPA_HIBERNATE_DDL_AUTO=update
   ```

## Database Setup

### Option 1: Render PostgreSQL (Recommended)
- Use the database defined in `render.yaml`
- Render will automatically provide connection details

### Option 2: External MySQL
- Keep your existing Aiven MySQL database
- Update environment variables with your connection details

## Health Checks

The application exposes health endpoints:
- `/actuator/health` - Application health status
- `/actuator/info` - Application information

## Troubleshooting

### Common Issues

1. **Build Failures**
   - Check Dockerfile syntax
   - Ensure all dependencies are in `pom.xml`

2. **Database Connection Issues**
   - Verify environment variables are set correctly
   - Check database is accessible from Render's network

3. **Port Issues**
   - Ensure application listens on port 8085
   - Check health check path is accessible

### Logs
- View deployment logs in Render Dashboard
- Check application logs for runtime errors

## Production Considerations

1. **Security**
   - Remove sensitive data from `application.properties`
   - Use environment variables for all secrets
   - Enable SSL/TLS

2. **Performance**
   - Consider upgrading from free plan for production
   - Monitor resource usage
   - Set up proper scaling

3. **Monitoring**
   - Set up alerts for health checks
   - Monitor error rates
   - Track response times
