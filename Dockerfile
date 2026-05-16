# Fase 1: De Build-fase
FROM quay.io/keycloak/keycloak:24.0 AS builder

ENV KC_DB=postgres

RUN /opt/keycloak/bin/kc.sh build --features="preview"

FROM quay.io/keycloak/keycloak:24.0

COPY --from=builder /opt/keycloak/ /opt/keycloak/

ENTRYPOINT ["/opt/keycloak/bin/kc.sh"]