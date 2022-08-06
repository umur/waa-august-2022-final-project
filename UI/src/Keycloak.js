import Keycloak from "keycloak-js";
const keycloak = new Keycloak({
  url: "http://localhost:8080",
  realm: "WAAProjectRealm",
  clientId: "waa-sso",
});

export default keycloak;
