FROM quay.io/keycloak/keycloak:22.0.3 as builder

ENV KC_HEALTH_ENABLED=true
ENV KC_METRICS_ENABLED=true
ENV KC_DB=postgres

WORKDIR /opt/keycloak
RUN /opt/keycloak/bin/kc.sh build

FROM registry.access.redhat.com/ubi9 AS ubi-micro-build
RUN mkdir -p /mnt/rootfs
RUN dnf install --installroot /mnt/rootfs curl --releasever 9 --setopt install_weak_deps=false --nodocs -y; dnf --installroot /mnt/rootfs clean all

FROM quay.io/keycloak/keycloak:22.0.3

COPY --from=ubi-micro-build /mnt/rootfs /
COPY --from=builder /opt/keycloak/ /opt/keycloak/

ENV KC_DB=postgres
ENV KC_DB_URL=jdbc:postgresql://keycloak-db:5432/postgres
ENV KC_DB_USERNAME=postgres
ENV KC_DB_PASSWORD=12345678
ENV KEYCLOAK_ADMIN=admin
ENV KEYCLOAK_ADMIN_PASSWORD=admin
ENV QUARKUS_TRANSACTION_MANAGER_ENABLE_RECOVERY=true
ENV KC_HOSTNAME_URL=https://localhost/
ENV KC_PROXY=edge
ENV HTTP_PROXY=http://nginx:80
ENV HTTPS_PROXY=https://nginx:443
ENV NO_PROXY=google.com

ENTRYPOINT ["/opt/keycloak/bin/kc.sh"]