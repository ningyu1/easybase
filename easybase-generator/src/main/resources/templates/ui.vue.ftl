<template>
    <div class="app-container">
        <div class="filter-container-card">
            <div class="text item">
                <el-form label-width="80px">
                    <el-row>
                        <el-col :span="6">
                            <el-form-item :label="$t('${(entity?replace("Entity", ""))?uncap_first}Table.name')" prop="name">
                                <el-input v-model="listQuery.name" :placeholder="$t('${(entity?replace("Entity", ""))?uncap_first}Table.name')" style="width: 180px;" class="filter-item" maxlength="32" @keyup.enter.native="handleFilter" />
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item :label="$t('${(entity?replace("Entity", ""))?uncap_first}Table.code')" prop="code">
                                <el-input v-model="listQuery.code" :placeholder="$t('${(entity?replace("Entity", ""))?uncap_first}Table.code')" style="width: 180px;" class="filter-item" maxlength="11" @keyup.enter.native="handleFilter" />
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item :label="$t('table.startDate')" prop="startDate">
                                <el-date-picker v-model.trim="listQuery.startDate" :placeholder="$t('table.startDate')" value-format="yyyy-MM-dd HH:mm:ss" type="datetime" style="width: 180px;" class="filter-item" />
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item :label="$t('table.endDate')" prop="endDate">
                                <el-date-picker v-model.trim="listQuery.endDate" :placeholder="$t('table.endDate')" value-format="yyyy-MM-dd HH:mm:ss" type="datetime" style="width: 180px;" class="filter-item" />
                            </el-form-item>
                        </el-col>
                        <el-col :span="6">
                            <el-form-item :label="$t('${(entity?replace("Entity", ""))?uncap_first}Table.status')" prop="status">
                                <el-select v-model="listQuery.status" :placeholder="$t('${(entity?replace("Entity", ""))?uncap_first}Table.status')" clearable style="width: 180px" class="filter-item" @change="handleFilter">
                                    <el-option v-for="item in statusOption" :key="item.key" :label="item.label" :value="item.key" />
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24">
                            <el-form-item style="float:right;">
                                <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">{{ $t('table.search') }}</el-button>
                                <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">{{ $t('table.add') }}</el-button>
                                <el-button class="filter-item" style="margin-left: 10px;" type="danger" icon="el-icon-delete" @click="handleBatchDelete">{{ $t('table.delete') }}</el-button>
                                <el-button v-waves :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">{{ $t('table.export') }}</el-button>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-form>
            </div>
        </div>

        <el-table
                :key="tableKey"
                v-loading="listLoading"
                :data="list"
                border
                fit
                highlight-current-row
                style="width: 100%;"
                @selection-change="handleSelectionChange"
        >
            <el-table-column type="selection" width="40" />
            <el-table-column :label="$t('${(entity?replace("Entity", ""))?uncap_first}Table.id')" align="center" width="50">
                <template slot-scope="scope">
                    <span>{{ scope.row.id }}</span>
                </template>
            </el-table-column>
            <el-table-column :label="$t('${(entity?replace("Entity", ""))?uncap_first}Table.code')" width="" align="center">
                <template slot-scope="scope">
                    <span>{{ scope.row.code }}</span>
                </template>
            </el-table-column>
            <el-table-column :label="$t('${(entity?replace("Entity", ""))?uncap_first}Table.name')" width="" align="center">
                <template slot-scope="scope">
                    <span>{{ scope.row.name }}</span>
                </template>
            </el-table-column>
            <el-table-column :label="$t('${(entity?replace("Entity", ""))?uncap_first}Table.description')" width="" align="center" show-overflow-tooltip>
                <template slot-scope="scope">
                    <span>{{ scope.row.description }}</span>
                </template>
            </el-table-column>
            <el-table-column :label="$t('${(entity?replace("Entity", ""))?uncap_first}Table.createTime')" width="" align="center">
                <template slot-scope="scope">
                    <span>{{ scope.row.createTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
                </template>
            </el-table-column>
            <el-table-column :label="$t('${(entity?replace("Entity", ""))?uncap_first}Table.status')" class-name="status-col" width="100">
                <template slot-scope="scope">
                    <el-tag :type="scope.row.status | statusFilter">{{ scope.row.status | statusNameFilter }}</el-tag>
                </template>
            </el-table-column>
            <el-table-column :label="$t('table.actions')" align="center" width="310" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <!-- <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">{{ $t('table.edit') }}</el-button>
                    <el-button v-if="scope.row.status!='1'" size="mini" type="success" @click="handleModifyStatus(scope.row,'1')">{{ $t('${(entity?replace("Entity", ""))?uncap_first}Table.enable') }}
                    </el-button>
                    <el-button v-if="scope.row.status!='0'" size="mini" @click="handleModifyStatus(scope.row,'0')">{{ $t('${(entity?replace("Entity", ""))?uncap_first}Table.disable') }}
                    </el-button>
                    <el-button size="mini" type="danger" @click="handleDelete(scope.row)">{{ $t('table.delete') }}
                    </el-button> -->
                    <i class="el-icon-edit" style="cursor:pointer;" @click="handleUpdate(scope.row)">{{ $t('table.edit') }}</i>
                    <i v-if="scope.row.status!='1'" class="el-icon-setting" style="cursor:pointer;" @click="handleModifyStatus(scope.row,'1')">{{ $t('${(entity?replace("Entity", ""))?uncap_first}Table.enable') }}</i>
                    <i v-if="scope.row.status!='0'" class="el-icon-setting" style="cursor:pointer;" @click="handleModifyStatus(scope.row,'0')">{{ $t('${(entity?replace("Entity", ""))?uncap_first}Table.disable') }}</i>
                    <i class="el-icon-delete" style="cursor:pointer;" @click="handleDelete(scope.row)">{{ $t('table.delete') }}</i>

                </template>
            </el-table-column>
        </el-table>
        <pagination v-show="total>0" :total="total" :page.sync="listQuery.current" :limit.sync="listQuery.size" @pagination="getList" />

        <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
            <el-form ref="form" :model="form" :rules="rules" label-width="100px" style="width: 400px; margin-left:50px;">
                <el-form-item :label="$t('${(entity?replace("Entity", ""))?uncap_first}Table.code')" prop="code">
                    <el-input v-model="form.code" placeholder="输入编号" maxlength="32" />
                </el-form-item>
                <el-form-item :label="$t('${(entity?replace("Entity", ""))?uncap_first}Table.name')" prop="name">
                    <el-input v-model="form.name" placeholder="输入名称" maxlength="32" />
                </el-form-item>
                <el-form-item :label="$t('${(entity?replace("Entity", ""))?uncap_first}Table.status')" prop="status">
                    <el-radio-group v-model="form.status">
                        <el-radio :label="1">启用</el-radio>
                        <el-radio :label="0">禁用</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item :label="$t('${(entity?replace("Entity", ""))?uncap_first}Table.description')">
                    <el-input v-model="form.description" :autosize="{ minRows: 2, maxRows: 4}" type="textarea" placeholder="输入备注信息" />
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">{{ $t('table.cancel') }}</el-button>
                <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">{{ $t('table.confirm') }}</el-button>
                <el-button v-else type="primary" @click="updateData">{{ $t('table.confirm') }}</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import { fetchList, create, del, update, batchDelete } from '@/api/system/${(entity?replace("Entity", ""))?uncap_first}'
    import waves from '@/directive/waves' // 水波纹指令
    import { parseTime } from '@/utils'
    import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

    export default {
        name: '${entity?replace("Entity", "")}Table',
        components: { Pagination },
        directives: {
            waves
        },
        filters: {
            statusFilter(status) {
                const statusMap = {
                    1: 'success',
                    0: 'danger'
                }
                return statusMap[status]
            },
            statusNameFilter(status) {
                const statusNameMap = {
                    1: '启用',
                    0: '禁用'
                }
                return statusNameMap[status]
            }
        },
        data() {
            return {
                multipleSelection: [],
                tableKey: 0,
                props: {
                    children: 'children'
                },
                list: null,
                total: 0,
                listLoading: true,
                listQuery: {
                    current: 1,
                    size: 20,
                    name: '',
                    code: '',
                    status: ''
                },
                statusOption: [
                    { label: '启用', key: '1' },
                    { label: '禁用', key: '0' }
                ],
                dialogFormVisible: false,
                dialogStatus: '',
                textMap: {
                    update: '编辑',
                    create: '添加'
                },
                form: {
                    id: '',
                    code: '',
                    name: '',
                    description: '',
                    status: 1
                },
                rules: {
                    code: [
                        { required: true, message: '请输入编号', trigger: 'blur' },
                        { min: 3, max: 16, message: '长度在 3 到 16 个字符', trigger: 'blur' }
                    ],
                    name: [
                        { required: true, message: '请输入名称', trigger: 'blur' },
                        { min: 3, max: 16, message: '长度在 3 到 16 个字符', trigger: 'blur' }
                    ],
                    status: [
                        { required: true, message: '请选择状态', trigger: 'change' }
                    ],
                    description: [
                        { required: true, message: '请填写备注信息', trigger: 'blur' }
                    ]
                },
                downloadLoading: false
            }
        },
        created() {
            this.getList()
        },
        methods: {
            handleSelectionChange(val) {
                this.multipleSelection = val
            },
            getList() {
                this.listLoading = true
                fetchList(this.listQuery).then(response => {
                    this.list = response.data
                    this.total = response.count
                    this.listLoading = false
                })
            },
            handleFilter() {
                this.listQuery.current = 1
                this.getList()
            },
            resetForm() {
                this.form = {
                    id: '',
                    code: '',
                    name: '',
                    description: '',
                    status: 1
                }
            },
            handleCreate() {
                this.resetForm()
                this.dialogStatus = 'create'
                this.dialogFormVisible = true
                this.$nextTick(() => {
                    this.$refs['form'].clearValidate()
                })
            },
            handleBatchDelete() {
                if (this.multipleSelection.length <= 0) {
                    this.$message({
                        type: 'error',
                        message: '请选择需要删除的数据!'
                    })
                    return
                }
                const data = []
                for (let i = 0; i < this.multipleSelection.length; i++) {
                    data[i] = this.multipleSelection[i].id
                }
                console.info(data)
                this.$confirm(
                    '此操作将永久删除数据是否继续?',
                    '提示',
                    {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }
                ).then(() => {
                    this.listLoading = true
                    batchDelete(data).then(() => {
                        this.listLoading = false
                        this.handleFilter()
                        this.$message({
                            type: 'success',
                            message: '删除成功!'
                        })
                    })
                }).catch(() => {
                })
            },
            createData() {
                this.$refs['form'].validate(valid => {
                    if (valid) {
                        create(this.form).then(() => {
                            this.dialogFormVisible = false
                            this.handleFilter()
                            this.$message({
                                message: '创建成功',
                                type: 'success'
                            })
                        })
                    }
                })
            },
            handleUpdate(row) {
                this.resetForm()
                this.form = Object.assign({}, row) // copy obj
                this.form.status = parseInt(this.form.status)
                this.dialogStatus = 'update'
                this.dialogFormVisible = true
                this.$nextTick(() => {
                    this.$refs['form'].clearValidate()
                })
            },
            updateData() {
                this.$refs['form'].validate(valid => {
                    if (valid) {
                        update(this.form).then(() => {
                            this.dialogFormVisible = false
                            this.handleFilter()
                            this.$message({
                                message: '更新成功',
                                type: 'success'
                            })
                        })
                    }
                })
            },
            handleDelete(row) {
                this.$confirm(
                    '此操作将永久删除该条数据：' + row.name + ', 是否继续?',
                    '提示',
                    {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }
                ).then(() => {
                    this.listLoading = true
                    del(row.id).then(() => {
                        this.listLoading = false
                        this.handleFilter()
                        this.$message({
                            type: 'success',
                            message: '删除成功!'
                        })
                    })
                }).catch(() => {
                })
            },
            handleModifyStatus(row, status) {
                this.listLoading = true
                const pa = {
                    id: row.id,
                    status: status
                }
                update(pa).then(() => {
                    this.listLoading = false
                    row.status = status
                    this.$message({
                        message: '状态修改成功',
                        type: 'success'
                    })
                })
            },
            handleDownload() {
                this.downloadLoading = true
                import('@/vendor/Export2Excel').then(excel => {
                    const tHeader = [
                        '序号',
                        '编号',
                        '名称',
                        '备注',
                        '创建时间',
                        '状态'
                    ]
                    const filterVal = [
                        'id',
                        'code',
                        'name',
                        'description',
                        'createTime',
                        'status'
                    ]
                    const data = this.formatJson(filterVal, this.list)
                    excel.export_json_to_excel({
                        header: tHeader,
                        data,
                        filename: '列表'
                    })
                    this.downloadLoading = false
                })
            },
            formatJson(filterVal, jsonData) {
                return jsonData.map(v =>
                    filterVal.map(j => {
                        if (j === 'createTime') {
                            return parseTime(v[j])
                        } else if (j === 'status') {
                            return this.$options.filters['statusNameFilter'](v[j])
                        } else {
                            return v[j]
                        }
                    })
                )
            }
        }
    }
</script>
