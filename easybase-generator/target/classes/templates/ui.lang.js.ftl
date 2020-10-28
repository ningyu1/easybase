export default {
    ${(entity?replace("Entity", ""))?uncap_first}Table: {
        id: '序号',
        name: '名称',
        code: '编号',
        status: '状态',
        description: '备注',
        enable: '启用',
        disable: '禁用',
        createTime: '创建时间'
    }
}