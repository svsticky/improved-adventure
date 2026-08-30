# Future Keycloak IAM instance of SV Sticky

## Running locally

1. Copy `sample.env` to `.env` and fill in `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, `ADMIN_PASSWORD`, `KEYCLOAK_PORT`, and `KC_HOSTNAME`.
2. Make sure realm data is checked out at `../realms/base` (sibling to this repo) — it's mounted into `/opt/keycloak/data/import` and imported on first startup.
3. Start it with:

   ```sh
   docker compose up --build
   ```

## Theming

`themes/main-theme` is a custom Keycloak theme (login, account, and email) applied via the `KC_SPI_THEME_*_NAME` environment variables in `docker-compose.yml`. Edit the files under `themes/` and restart the container to see changes (theme caching is disabled in the compose config).

## Custom providers

This repo vendors the source of two custom Keycloak SPI providers:

- `keycloak-bcrypt-provider/` — login-flow `Authenticator` that verifies legacy bcrypt password hashes imported from Koala and migrates them to native Keycloak credentials on first login.
- `email-listener/` — `EventListenerProvider` that fires a webhook when a user's email is updated.

The `providers/` folder holds the **built jars** (`keycloak-bcrypt.jar`, `email-listener.jar`) that `docker-compose.yml` mounts into the Keycloak container — it does not build from source automatically. After changing either provider's source (or bumping its `keycloak.version` in `pom.xml`, e.g. when upgrading Keycloak), you must rebuild and copy the jar over manually:

```sh
cd keycloak-bcrypt-provider && mvn package && cp target/keycloak-bcrypt-provider-1.0.0.jar ../providers/keycloak-bcrypt.jar
cd email-listener && mvn package && cp target/email-listener.jar ../providers/email-listener.jar
```

Forgetting this step means the container keeps running the old jar even though the source has changed.
