import axios from 'axios';

let base = '';
export const uploadFileRequest = (url, params) => {
    return axios({
      method: 'post',
      url: `${base}${url}`,
      data: params,
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    });
}

export const postRequest = (url, params) => {
  return axios({
      method: 'post',
      url: `${base}${url}`,
      data: params,
      // headers: {
      //   'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
      // }
  })
}


export const putRequest = (url, params) => {
  return axios({
      method: 'put',
      url: `${base}${url}`,
      data: params,
      // headers: {
      //   'Content-Type': 'application/json'
      // }
  })
}
export const getRequest = (url, params) => {
  return axios({
      method: 'get',
      url: `${base}${url}`,
      params: params
  })
}
export const deleteRequest = (url, params) => {
  return axios({
      method: 'delete',
      url: `${base}${url}`,
      params: params
  })
}