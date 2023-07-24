import request from '@/utils/request'

// 提交保存xml
export function saveProcessXml(data) {
    return request({
        url: '/activiti/saveProcessXml',
        method: 'post',
        data: data
    })
}
// 根据xml部署流程
export function deployProcessByXml(data) {
    return request({
        url: '/activiti/deployProcessByXml',
        method: 'post',
        data: data
    })
}