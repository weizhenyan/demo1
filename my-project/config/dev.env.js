'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  BASE_API: '"//127.0.0.1:8083"', // 这里就是配置开发环境接口的地方,在开发时候就是本地机器的地址,端口号就是你配置的webpack 端口号
})
