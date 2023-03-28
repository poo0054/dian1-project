<template>
    <el-dialog :visible.sync="show" :title="导入" width="402px">
        <el-upload
                ref="uploadRef"
                drag
                action=""
                :http-request="uploadFile"
                :limit="1"
                :file-list="fileList"
                :auto-upload="false"
                :on-remove="handleOnChange"
                :on-change="handleOnChange"
        >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        </el-upload>
        <p class="download-link"><a @click="handleDownloadTemplate">下载模板</a></p>
        <div slot="footer" class="dialog-footer">
            <el-button @click="hideDialog()">取 消</el-button>
            <el-button type="primary" :loading="loading" @click="handleSubmit()">确 定</el-button>
        </div>
    </el-dialog>
</template>
<script>
    import VueCookies from "vue-cookies";

    export default {
        data() {
            return {
                type: "type",
                show: false,
                fileList: [],
                loading: false,
                header: {
                    Authorization: "Bearer " + VueCookies.get("access_token")
                }
            };
        },
        computed: {
            title() {
            }
        },
        methods: {
            handleDownloadTemplate() {
                const downloadUrl = `${tableClassName}/downloadTemplate`;

                this.$api.downLoad("get", downloadUrl).then((res) => {
                    if (res) {
                        this.$tools.saveBlob(res, `${r'${'}this.title${r'}'}`);
                    }
                });
            },
            handleOnChange(file, fileList) {
                this.fileList = fileList;
            },
            uploadFile() {
                const uploadUrl = `${tableClassName}/upload`;
                const formData = new FormData();
                formData.append("file", this.fileList[0].raw);
                this.$api.file(uploadUrl, formData).then((res) => {
                    if (res) {
                        this.$message.success("操作成功");
                        this.$emit("onUploadSuccess");
                        this.hideDialog();
                    }
                });
            },
            handleSubmit() {
                if (!this.fileList.length) {
                    this.$message.warning("请选择要上传的文件");
                    return;
                }
                this.$refs["uploadRef"].submit();
            },
            showDialog(type) {
                this.type = type;
                this.show = true;
            },
            hideDialog() {
                this.fileList = [];
                this.show = false;
            }
        }
    };
</script>
<style scoped>
    .download-link {
        color: #455e9e;
        text-decoration: underline;
        margin-top: 10px;
    }
</style>
