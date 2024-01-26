import UserService from "../../config/UserService";
import KeycloakLoading from "./KeycloakLoading";

const RenderOnAuthenticated = ({ children }) => (UserService.isLoggedIn()) ? children : <KeycloakLoading>{children}</KeycloakLoading>;

export default RenderOnAuthenticated;