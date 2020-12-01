import request from '@/utils/request';

export async function query(): Promise<any> {
  return request('/api/users');
}

export async function queryCurrent(): Promise<any> {
  const userInfo = localStorage.getItem('userInfo');
  const userId = JSON.parse(userInfo)['userId'];
  console.log("登录用户ID{"+userId+"}");
  /** return request('/api/currentUser'); */
  return request('/system/api/sysUser/selectOne', {
    method: 'POST',
    data: {"userId":userId},
  });
}

export async function queryNotices(): Promise<any> {
  return request('/api/notices');
}
