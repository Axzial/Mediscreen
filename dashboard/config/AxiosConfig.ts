import axios, {AxiosError} from 'axios';

// @ts-ignore
axios.defaults.headers['Access-Control-Allow-Origin'] = '*';

axios.interceptors.response.use(
    response => response,
    error => Promise.reject(error as AxiosError<any>)
)
