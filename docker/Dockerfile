ARG REDHAT_REGISTRY=registry.access.redhat.com
ARG JAVA_BUILD_IMAGE=ubi8/openjdk-17:1.17-1
ARG JAVA_RUNTIME_IMAGE=ubi8/openjdk-17-runtime:1.17-1

# Build
FROM $REDHAT_REGISTRY/$JAVA_BUILD_IMAGE as builder
WORKDIR /opt/app-root/src
COPY . .
ARG MAVEN_REPOSITORY=https://repo.maven.apache.org/maven2
RUN mvn clean package -DskipTests -Dmaven.repo.remote=${MAVEN_REPOSITORY}

# Run
FROM $REDHAT_REGISTRY/$JAVA_RUNTIME_IMAGE

USER root
# Add default user
RUN mkdir -p /opt/app-root/app && \
    useradd -u 1001 -r -g 0 -d /opt/app-root -s /sbin/nologin \
      -c "Default Application User" app

USER 1001
WORKDIR /opt/app-root/app
COPY --from=builder --chown=1001:0 /opt/app-root/src/target/FileService-*.jar ./FileService.jar

ENTRYPOINT ["java", "-jar", "FileService.jar"]