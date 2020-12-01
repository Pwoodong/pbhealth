import request from '@/utils/request';
import { TableListParams, TableListItem } from './data.d';

export async function queryRule(params?: TableListParams) {
  return request('/collect/dataCollect/getPageList', {
    params,
  });
}

export async function removeRule(params: { key: number[] }) {
  return request('/collect/dataCollect/delete', {
    method: 'POST',
    data: {
      ...params,
      method: 'delete',
    },
  });
}

export async function addRule(params: TableListItem) {
  return request('/collect/dataCollect/save', {
    method: 'POST',
    data: {
      ...params,
      method: 'post',
    },
  });
}

export async function updateRule(params: TableListItem) {
  return request('/collect/dataCollect/save', {
    method: 'POST',
    data: {
      ...params,
      method: 'update',
    },
  });
}
