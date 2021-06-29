<template>
    <div>
        <template v-for="menu in menus">
            <!-- 菜单组 -->
            <el-submenu v-if="menu.children != null" :index="''+menu.path" :key="menu.path">
                <template slot="title">
                    <i :class="menu.icon"></i>
                    <span slot="title" >{{menu.name}}</span>
                </template>
                <menu-item :menus="menu.children"></menu-item>
            </el-submenu>
            <!-- 菜单项 -->
            <el-menu-item v-else :index="''+menu.path+menu.resourceId" :key="menu.path" :route="menu.path+menu.resourceId">
                <i :class="menu.icon"></i>
                <span slot="title" >{{menu.name}}</span>
            </el-menu-item>
        </template>
    </div>
</template>

<script>
    export default {
        name: "menu-item",
        props: {
            menus: {
                type: Array,
                default: function () {
                    return []
                }
            }
        },
    }
</script>

<style scoped>

</style>
