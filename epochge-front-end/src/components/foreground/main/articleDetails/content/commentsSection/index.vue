<template>
    <div class="commentBox">
        <h3>文章评论</h3>
        <p>评论出精彩，精彩原与你！</p>
        <div class="comment_issue">
            <el-input @click="VerifyingLogin(ref)" :readonly="readonly" v-model="commentContent" maxlength="100"
                placeholder="留下你的精彩评论吧" :autosize="{ minRows: 4, maxRows: 6 }" show-word-limit type="textarea"
                style="margin-bottom: 10px;" />
            <div style=" text-align:right">
                <el-button plain color="#2fa7b9" @click="submitAdd(0)">提交</el-button>
            </div>

        </div>
        <h3>最新评论</h3>
        <p>{{commentCount}}条评论</p>
        <div class="all_comment">
            <div class="comment" v-for="(item,index) in commentInfo" :key="index">
                <div class="comment_left">
                    <!-- 用户没设置头像的情况下使用系统默认的 -->
                    <img v-if="item.userIcon !== null" :src="url+item.userIcon" />
                    <img v-if="item.userIcon === null" src="../../../../../../assets/default_avatar.svg" alt="">
                </div>
                <div class="comment_right">
                    <div class="comment_name">
                        <p>{{item.userName}}<span v-if="item.userId == articleInfo.userId"
                                class="comment_author">作者</span></p>
                    </div>
                    <div class="comment_content">
                        <p>{{item.content}}</p>
                    </div>
                    <div class="comment_else">
                        <p>
                            <span>{{item.commentDate}}</span>
                            <!-- 显示删除功能   1、当前文章为当前登录用户的 2、管理员用户 -->
                            <el-popconfirm confirm-button-text="确定" cancel-button-text="取消" :icon="Delete"
                                icon-color="#F56C6C" confirm-button-type="danger" :title="'确定删除该评论吗？'"
                                @confirm="deleteComment(item.commentId)">
                                <template #reference>
                                    <span class="reply"
                                        v-if="userInfo.userId === articleInfo.userId || userInfo.userType === 0 || item.userId === userInfo.userId">
                                        <el-icon>
                                            <Delete />
                                        </el-icon>
                                        删除
                                    </span>
                                </template>
                            </el-popconfirm>
                            <span class="reply" @click="reply(item.commentId)">
                                <el-icon>
                                    <ChatDotSquare />
                                </el-icon>
                                <span v-if="item.commentId !== replyId">回复</span>
                                <span v-if="item.commentId === replyId">取消回复</span>
                            </span>
                        </p>
                    </div>
                    <div v-show="item.commentId === replyId" class="comment_reply_box">
                        <el-input v-model="replyIdContent" maxlength="100" :placeholder="'回复@' + item.userName+ ':'"
                            :autosize="{ minRows: 4, maxRows: 6 }" show-word-limit type="textarea"
                            style="margin-bottom: 10px;" />
                        <div style=" text-align:right">
                            <el-button plain color="#2fa7b9" @click="submitAdd(item.commentId)">提交</el-button>
                        </div>
                    </div>
                    <!-- 子评论 -->
                    <div class="comment_child" v-if="item.child.length > 0" v-for="(val,itemIndex) in item.child"
                        :key="itemIndex">
                        <div class="comment_child_content">
                            <div class="comment_child_left">
                                <!-- 用户没设置头像的情况下使用系统默认的 -->
                                <img v-if="val.userIcon !== null" :src="url+val.userIcon" />
                                <img v-if="val.userIcon === null" src="../../../../../../assets/default_avatar.svg"
                                    alt="">
                            </div>
                            <div class="comment_child_right">
                                <div class="comment_child_name">
                                    <p>{{val.userName}}<span v-if="val.userId == articleInfo.userId"
                                            class="comment_author">作者</span></p>
                                </div>
                                <div class="comment_content">
                                    <p>{{val.content}}</p>
                                </div>
                                <div class="comment_else">
                                    <p>
                                        <span>{{val.commentDate}}</span>
                                        <!-- 显示删除功能   1、当前文章为当前登录用户的 2、管理员用户 -->
                                        <el-popconfirm confirm-button-text="确定" cancel-button-text="取消" :icon="Delete"
                                            icon-color="#F56C6C" confirm-button-type="danger" :title="'确定删除该评论吗？'"
                                            @confirm="deleteComment(val.commentId)">
                                            <template #reference>
                                                <span class="reply"
                                                    v-if="userInfo.userId === articleInfo.userId || userInfo.userType ===0 || val.userId === userInfo.userId">
                                                    <el-icon>
                                                        <Delete />
                                                    </el-icon>
                                                    删除
                                                </span>
                                            </template>
                                        </el-popconfirm>
                                        <span class="reply" @click="reply(val.commentId)">
                                            <el-icon>
                                                <ChatDotSquare />
                                            </el-icon>
                                            <span v-if="val.commentId !== replyId">回复</span>
                                            <span v-if="val.commentId === replyId">取消回复</span>
                                        </span>
                                    </p>
                                </div>
                                <div v-show="val.commentId === replyId" class="comment_reply_box">
                                    <el-input v-model="replyIdContent" maxlength="100"
                                        :placeholder="'回复@' + val.userName+ ':'" :autosize="{ minRows: 4, maxRows: 6 }"
                                        show-word-limit type="textarea" style="margin-bottom: 10px;" />
                                    <div style=" text-align:right">
                                        <el-button plain color="#2fa7b9" @click="submitAdd(val.commentId)">提交
                                        </el-button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div v-if="val.child">
                            <CommentItem :commentChild="val.child" :reply="val.userName" :authorId="articleInfo.userId"
                                :replyId="replyId" @onGetValue="reply" @onGetReplyIdContent="submitAdd2"
                                :userInfo="userInfo" @onDeleteComment="deleteComment">
                            </CommentItem>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    // 递归3级评论
    import CommentItem from './commentItem/index.vue'
    import {
        Delete
    } from '@element-plus/icons-vue'
    import {
        ref,
        toRefs,
        reactive,
        onMounted,
        watch,
        onUpdated
    } from 'vue'

    import {
        useRouter,
        useRoute
    } from 'vue-router';

    import {
        ElMessage,
        ElMessageBox
    } from 'element-plus'

    import axios from "axios";
    /**
     * @desc 加载获取当前文章的评论数据
     */
    function loadData(state) {
        const params = {
            articleId: state.articleInfo.articleId
        }
        axios.get("/showCommentByArticleId", {
            params
        }).then(res => {
            console.log(res.data);
            state.commentInfo = res.data;
        })
    }


    export default {
        components: {
            CommentItem
        },
        props: {
            articleInfo: Object
        },
        setup(props) {
            // 获取当前登录的用户信息
            var tokenStr = sessionStorage.getItem("userInfo");
            //挂载后加载数据
            onMounted(() => {
                loadData(state);
                if (tokenStr) {
                    state.userInfo = JSON.parse(tokenStr).data[0]
                }
            });
            // 数据加载后    查询当前有多少条评论  
            onUpdated(() => {
                state.commentCount = document.querySelectorAll('.comment').length + document.querySelectorAll(
                    '.comment_child').length
            })
            const state = reactive({
                // 当前文章信息
                articleInfo: props.articleInfo,
                // 当前文章对应的评论信息
                commentInfo: [],
                // 评论内容
                commentContent: "",
                // 回复内容
                replyIdContent: '',
                // 回复状态
                replyId: false,
                // 评论框 只读
                readonly: true,
                // 总评论数
                commentCount: '',
                // 登录信息
                userInfo: {},
                // 服务器路径
                url: process.env.VUE_APP_URL,
            })

            const router = useRouter();
            const route = useRoute();

            // 评论表单获得焦点 校验是否登录
            const VerifyingLogin = (event) => {
                if (!tokenStr) {
                    ElMessageBox.confirm(
                        '当前系统检测到您未登录，登录即可解锁更多精彩内容',
                        '提示', {
                            confirmButtonText: '前往登录',
                            cancelButtonText: '取消',
                            type: 'warning',
                        }
                    ).then(() => {
                        router.push({
                            path: '/login',
                            query: {
                                redirect: route.path
                            } // 登录成功后返回当前页面
                        })
                    })
                } else {
                    state.readonly = false
                }
            }

            // 回复评论
            const reply = (id) => {
                // 判断是否登录，没有登录前往登录界面
                if (!tokenStr) {
                    ElMessageBox.confirm(
                        '当前系统检测到您未登录，登录即可解锁更多精彩内容',
                        '提示', {
                            confirmButtonText: '前往登录',
                            cancelButtonText: '取消',
                            type: 'warning',
                        }
                    ).then(() => {
                        router.push({
                            path: '/login',
                            query: {
                                redirect: route.path
                            } // 登录成功后返回当前页面
                        })
                    })
                } else {
                    // 关闭回复
                    if (state.replyId === id) {
                        state.replyId = false;
                    }
                    // 当前评论没有关闭又点击其他回复时
                    else if (state.replyId !== id && state.replyId !== false) {
                        state.replyId = id
                    } else { // 为false时
                        state.replyId = id
                    }
                }
            }

            // 提交评论   
            const submitAdd = (parentId) => {
                // 判断是否登录，没有登录前往登录界面
                if (!tokenStr) {
                    ElMessageBox.confirm(
                        '当前系统检测到您未登录，登录即可解锁更多精彩内容',
                        '提示', {
                            confirmButtonText: '前往登录',
                            cancelButtonText: '取消',
                            type: 'warning',
                        }
                    ).then(() => {
                        router.push({
                            path: '/login',
                            query: {
                                redirect: route.path
                            } // 登录成功后返回当前页面
                        })
                    })
                } else {
                    if (state.commentContent !== "" || state.replyIdContent !== "") {
                        const params = {
                            // 当前文章id
                            articleId: state.articleInfo.articleId,
                            // 评论用户id
                            userId: state.userInfo.userId,
                            // 评论内容
                            content: parentId == 0 ? state.commentContent : state.replyIdContent,
                            // 父评论  没有为0
                            parentId: parentId
                        }
                        axios.post("/comment/insertCommentInfo",
                            params
                        ).then(res => {
                            if (parentId == 0) {
                                // 正常评论
                                state.commentContent = ""
                            } else {
                                // 回复
                                state.replyIdContent = ""
                                // 回复表单
                                state.replyId = false
                            }
                            // 刷新评论数据
                            loadData(state);
                            ElMessage({
                                message: '评论成功',
                                type: 'success',
                            })
                        })
                    } else {
                        ElMessage.error('表达你的态度再评论吧')
                    }

                }
            }

            // 回复评论
            const submitAdd2 = (parentId, replyContent) => {
                // 判断是否登录，没有登录前往登录界面
                if (!tokenStr) {
                    ElMessageBox.confirm(
                        '当前系统检测到您未登录，登录即可解锁更多精彩内容',
                        '提示', {
                            confirmButtonText: '前往登录',
                            cancelButtonText: '取消',
                            type: 'warning',
                        }
                    ).then(() => {
                        router.push({
                            path: '/login',
                            query: {
                                redirect: route.path
                            } // 登录成功后返回当前页面
                        })
                    })
                } else {
                    if (replyContent !== "") {
                        const params = {
                            // 当前文章id
                            articleId: state.articleInfo.articleId,
                            // 评论用户id
                            userId: state.userInfo.userId,
                            // 评论内容
                            content: replyContent,
                            // 父评论  没有为0
                            parentId: parentId
                        }
                        console.log(params);
                        axios.post("/comment/insertCommentInfo",
                            params
                        ).then(res => {
                            if (parentId == 0) {
                                // 正常评论
                                state.commentContent = ""
                            } else {
                                // 回复
                                state.replyIdContent = ""
                                // 回复表单
                                state.replyId = false
                            }
                            // 刷新评论数据
                            loadData(state);
                            ElMessage({
                                message: '评论成功',
                                type: 'success',
                            })
                        })
                    } else {
                        ElMessage.error('表达你的态度再评论吧')
                    }

                }
            }

            // 删除评论
            const deleteComment = (commentId) => {
                axios.delete("/comment/delete", {
                    params: {
                        commentId: commentId
                    }
                }).then(res => { // 删除成功
                    if (res.data.code == 0) {
                        // 刷新评论数据
                        loadData(state);
                    } else {
                        ElMessage.error('删除失败')
                    }

                })
            }


            return {
                ...toRefs(state),
                reply,
                submitAdd,
                submitAdd2,
                VerifyingLogin,
                deleteComment,
                Delete // 图标
            }
        }
    }
</script>

<style>
    .commentBox {
        height: auto;
        overflow: hidden;
        background-color: white;
        padding: 25px;
        color: #474749;
        border-radius: 10px;
        margin-bottom: 20px;
    }

    .commentBox>p {
        border-bottom: #dbdbdb 1px dashed;
        line-height: 30px;
        color: #888;
        font-size: 13px;
        padding-bottom: 5px;
        margin-bottom: 20px;
    }

    /* 评论区样式 */
    .comment {
        /* width: 100%;
        float: left; */
        display: flex;
    }

    /* .comment_left {
        width: 40px;
        height: auto;
        margin-right: 10px;
    } */

    .comment_left>img {
        width: 40px;
        border-radius: 50px;
        object-fit: cover;
        overflow: hidden;
        margin-right: 10px;
    }

    .comment_right {
        /* width: 85%; */
        height: auto;
        /* float: left; */
        line-height: 25px;
    }

    .comment_name {
        color: #6F6F6F;
        font-size: 14px;
    }

    .comment_author {
        padding: 0 3px;
        background: #2fa7b9;
        color: white;
        margin-left: 5px;
        border-radius: 3px;
    }

    .comment_else {
        font-size: 14px;
        color: #b1b1b1;
        margin-bottom: 10px;
    }

    .comment_else span {
        margin-right: 15px;
        margin-right: 15px;
        display: inline-flex;
        justify-content: center;
        align-items: center;
    }

    .comment_else span .el-icon {
        margin-right: 5px;
    }

    .comment_else .reply {
        color: #777777;
        cursor: pointer;
        font-size: 12px;
    }

    .comment_child_content {
        display: flex;
    }

    /* .comment_child_left {
        width: 30px;
        height: auto;
        margin-right: 8px;
    } */

    .comment_child_name {
        color: #6F6F6F;
        font-size: 13px;
    }

    .comment_child_left img {
        width: 40px;
        border-radius: 50px;
        object-fit: cover;
        overflow: hidden;
        margin-right: 10px;
    }

    .comment_child_right {
        /* width: 90%;
        height: auto;
        float: left; */
        height: auto;
        line-height: 25px;
    }
</style>