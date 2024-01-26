import UserService from "../../config/UserService";

const RenderOnRole = ({ roles, children }) => UserService.hasRole(roles) ? children : null;

export default RenderOnRole;