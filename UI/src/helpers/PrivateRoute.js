import { useKeycloak } from "@react-keycloak/web";
import RedirectToLogin from './../RedirectToLogin'
const PrivateRoute = ({ children }) => {
  debugger
  const { keycloak } = useKeycloak();

  const isLoggedIn = keycloak.authenticated;
  if(isLoggedIn==null){
    return null
  }
  return isLoggedIn ? children : <RedirectToLogin/> ;
};

export default PrivateRoute;
