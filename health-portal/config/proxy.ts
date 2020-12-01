/**
 * 在生产环境 代理是无法生效的，所以这里没有生产环境的配置
 * The agent cannot take effect in the production environment
 * so there is no configuration of the production environment
 * For details, please see
 * https://pro.ant.design/docs/deploy
 */
export default {
  dev: {
    '/system/api/': {
      /** target: 'https://preview.pro.ant.design', */
      target: 'http://localhost:8882/health-system',
      changeOrigin: true,
      pathRewrite: { '^/system/api': '' },
    },
    '/collect/': {
      target: 'http://127.0.0.1:8880/health-collect',
      changeOrigin: true,
      pathRewrite: { '^/collect': '' },
    },
  },
  test: {
    '/api/': {
      target: 'https://preview.pro.ant.design',
      changeOrigin: true,
      pathRewrite: { '^': '' },
    },
  },
  pre: {
    '/api/': {
      target: 'your pre url',
      changeOrigin: true,
      pathRewrite: { '^': '' },
    },
  },
};
