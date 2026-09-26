package nl.svsticky.tavern.keycloak;

import org.keycloak.Config;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;

import java.util.Collections;
import java.util.List;

public class BcryptPasswordAuthenticatorFactory implements AuthenticatorFactory {

    public static final String PROVIDER_ID = "bcrypt-password-form";

    private static final BcryptPasswordAuthenticator SINGLETON = new BcryptPasswordAuthenticator();

    private static final AuthenticationExecutionModel.Requirement[] REQUIREMENT_CHOICES = {
            AuthenticationExecutionModel.Requirement.REQUIRED,
            AuthenticationExecutionModel.Requirement.DISABLED
    };

    @Override
    public String getId() {
        return PROVIDER_ID;
    }

    @Override
    public String getDisplayType() {
        return "Bcrypt Password Form (Legacy Migration)";
    }

    @Override
    public String getReferenceCategory() {
        return "password";
    }

    @Override
    public boolean isConfigurable() {
        return false;
    }

    @Override
    public AuthenticationExecutionModel.Requirement[] getRequirementChoices() {
        return REQUIREMENT_CHOICES;
    }

    @Override
    public boolean isUserSetupAllowed() {
        return false;
    }

    @Override
    public String getHelpText() {
        return "Uses the standard Keycloak password when the user has one. Otherwise verifies the password "
                + "against the 'legacy_bcrypt_hash' user attribute (imported from Koala) and, on success, "
                + "migrates the credential to PBKDF2 and removes the attribute.";
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return Collections.emptyList();
    }

    @Override
    public Authenticator create(KeycloakSession session) {
        return SINGLETON;
    }

    @Override
    public void init(Config.Scope config) {
        // no configuration needed
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
        // no cross-provider wiring needed
    }

    @Override
    public void close() {
        // stateless; nothing to release
    }
}
