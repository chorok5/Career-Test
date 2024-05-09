module.exports = {
  devServer: {
    proxy: {
        '/':{
            "target":'http://localhost:3000', // Spring boot의 주소 및 포트
            "pathRewrite":{'^/':''},
            "changeOrigin":true,
        }
    }
  }
}
