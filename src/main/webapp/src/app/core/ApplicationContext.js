import App from "../app.js";
import Router from "../routing.module.js";

const useNavigate = () => App.router ? App.router : new Router();

export {
    useNavigate
}