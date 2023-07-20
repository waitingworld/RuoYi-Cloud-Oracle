import request from '@/utils/request'

// 提交保存xml
export function saveProcessXml(data) {
    return request({
        url: '/activiti/saveProcessXml',
        method: 'post',
        data: data
    })
}