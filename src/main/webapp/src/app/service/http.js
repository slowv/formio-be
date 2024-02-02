import LogUtils from "../utils/log.util.js";
import axios from "https://cdn.skypack.dev/axios";
// import _ from "/lodash/lodash.js";
export default class Http {

    #axios;
    #log = new LogUtils('~.a.s.Http');

    constructor() {
        this.#axios = axios.create(this.#getHttpConfig());

        // this.#addInterceptor();
    }

    #getHeaders() {
        return {
            headers: {
                'Content-Type': 'application/json'
            }
        }
    }

    /**
     * @param headers is {}
     * @param config is {@link AxiosRequestConfig}
     * */
    #getHttpConfig(headers = {}, config = {}) {
        return {...config, ...headers};
    }

    // #addInterceptor() {
    //     this.#axios.interceptors.response.use(
    //         (response) => {
    //             return Promise.resolve(response)
    //         },
    //         (error) => {
    //             if (error) {
    //                 const status = _.get(error, 'response.status', 0)
    //                 if (status && status === 401) {
    //                     this.#log.error()
    //                     return Promise.reject(error)
    //                 }
    //             }
    //             return Promise.reject(error)
    //         },
    //     );
    // }

    _get(url, httpConfig = this.#getHttpConfig(this.#getHeaders())) {
        return new Promise((resolve, reject) => {
            this.#axios.get(url, {...httpConfig}).then(
                data => resolve(data),
                err => reject(err)
            )
        })
    }

    _post(url, data = {}, httpConfig = this.#getHttpConfig(this.#getHeaders)) {
        return new Promise((resolve, reject) => {
            this.#axios.post(url, data, {...httpConfig}).then(
                (data) => resolve(data),
                (err) => reject(err)
            )
        })
    }
}