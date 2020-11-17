import request from '@/utils/request';
import { TableListParams, TableListItem } from './data.d';

export async function queryRule(params?: TableListParams) {
  return request('http://127.0.0.1:8880/health-collect/dataCollect/getPageList', {
    params,
  });
}

export async function removeRule(params: { key: number[] }) {
  return request('http://127.0.0.1:8880/health-collect/dataCollect/delete', {
    method: 'POST',
    data: {
      ...params,
      method: 'delete',
    },
  });
}

export async function addRule(params: TableListItem) {
  return request('http://127.0.0.1:8880/health-collect/dataCollect/save', {
    method: 'POST',
    data: {
      ...params,
      method: 'post',
    },
  });
}

export async function updateRule(params: TableListParams) {
  return request('http://127.0.0.1:8880/health-collect/dataCollect/save', {
    method: 'POST',
    data: {
      ...params,
      method: 'update',
    },
  });
}
