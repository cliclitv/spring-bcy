const fs = require('fs')


const yml =(databaseurl, databaseuser, databasepwd)=> `
# spring.application.name=bcy
spring:
    application:
        name: 'bcy'
    web:
        resources:
            static-locations: "classpath:/dist"
    datasource:
        url: jdbc:sqlite:\${user.dir}/src/main/resources/db/bcy.db
        driver-class-name: org.sqlite.JDBC

mybatis:
    mapper-locations: classpath:/mapper/*.xml
        
server:
    port: 8888
    servlet:
        encoding:
            charset: 'UTF-8'
            enabled: true
            force: true
`

const argv = process.argv.slice(2)

const databaseurl = argv[0] || 'jdbc:mysql://localhost:3306/bcy'
const databaseuser = process.env.databaseuser || 'root'
const databasepwd = argv[1] || 'phpts'


fs.writeFileSync('./src/main/resources/application.yml', yml(databaseurl, databaseuser, databasepwd), 'utf8');
