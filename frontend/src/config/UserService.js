import Keycloak from 'keycloak-js';
const kc = new Keycloak('/keycloak.json');

const initKeycloak = (onAuthenticatedCallback) => {
    kc.init({
      enableLogging: true,
      onLoad: 'check-sso',
      checkLoginIframe: false,
      pkceMethod: 'S256'
    })
      .then(() => {
        onAuthenticatedCallback();
      })
      .catch((error) => {
        console.error('Failed to initialize adapter:', error);
      });
  };
  
  const doLogin = kc.login;
  
  const doLogout = kc.logout;
  
  const getToken = () => kc.token;
  
  const getTokenParsed = () => kc.tokenParsed;
  
  const isLoggedIn = () => !!kc.token;
  
  const updateToken = (successCallback) =>
    kc.updateToken(5)
      .then(successCallback)
      .catch(doLogin);
  
  const getUsername = () => kc.tokenParsed?.preferred_username;

  const getUserId = () => kc.subject;

  const hasRole = (role) => kc.tokenParsed?.['resource_access.test-realm-sso.roles']?.includes(role) ? true : false;
  
  const UserService = {
    initKeycloak,
    doLogin,
    doLogout,
    isLoggedIn,
    getToken,
    getTokenParsed,
    updateToken,
    getUsername,
    getUserId,
    hasRole,
  };

export default UserService;