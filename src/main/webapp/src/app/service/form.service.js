import Http from "./http.js";
import {API_FORM, BASE_URL} from "../constants/api.constant.js";


export default class FormService extends Http {
    static #instance;

    getForms() {
        return new Promise((resolve, reject) => {
            $.ajax({
                url: BASE_URL + API_FORM.index,
                method: 'GET',
                success: (res) => {
                    resolve(res)
                },
                error: (err) => {
                    reject(err)
                }
            })
        })
    }

    getForm(formId) {
        return new Promise((resolve, reject) => {
            $.ajax({
                url: BASE_URL + API_FORM.show.replace(":id", formId),
                method: 'GET',
                success: (res) => {
                    resolve(res)
                },
                error: (err) => {
                    reject(err)
                }
            })
        })
    }

    static getInstance() {
        if (!this.#instance) {
            this.#instance = new FormService();
        }
        return this.#instance;
    }
}