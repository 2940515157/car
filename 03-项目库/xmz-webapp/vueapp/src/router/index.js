import Router from "vue-router"
import Layout from "../components/layout/index.vue"

/** 系统配置 */
import Resource from "@/views/system/resource/index.vue"
import Permission from "@/views/system/permission/index.vue"
import Operation from "@/views/system/operation/index.vue"
/** 基本数据 */
import Dictionaries from "@/views/basedata/dictionaries/index.vue"
import Geographic from "@/views/basedata/geographic/index.vue"
/** 车型/配件管理 */
import CarBrand from "@/views/cars-parts/car_brand/index.vue"
import CarFactory from "@/views/cars-parts/car_factory/index.vue"
import CarCategory from "@/views/cars-parts/car_category/index.vue"
import MaintainTypeServ from "@/views/cars-parts/maintain_type_serv/index.vue"
import MaintainCycle from "@/views/cars-parts/maintain_cycle/index.vue"
import PartsType from "@/views/cars-parts/parts-type/index.vue"
import CarParts from "@/views/cars-parts/car_parts/index.vue"
/** 服务产品 */
import ServCatalog from "@/views/service/serv_catalog/index.vue"
import ServObject from "@/views/service/serv_object/index.vue"
import CheckupDirectory from "@/views/service/checkup_directory/index.vue"
import CheckupCatalog from "@/views/service/checkup_catalog/index.vue"
import CheckupPkg from "@/views/service/checkup_pkg/index.vue"
/** 人力资源 */
import Org from "@/views/hr/org/index.vue"
import Area from "@/views/hr/area/index.vue"
import Branches from "@/views/hr/branches/index.vue"
import BranchesBank from "@/views/hr/branches_bank/index.vue"
import Employees from "@/views/hr/employees/index.vue"
import Role from "@/views/hr/role/index.vue"
import Account from "@/views/hr/account/index.vue"
import Visit from "@/views/hr/visit/index.vue"
/** 卡券管理 */
import CardSetup from "@/views/card/card_setup/index.vue"
import Package from "@/views/card/package/index.vue"
import CompositeProduct from "@/views/card/composite_product/index.vue"
import Coupon from "@/views/card/coupon/index.vue"
import Card from "@/views/card/card/index.vue"
import BrCard from "@/views/card/br_card/index.vue"
import BatchCard from "@/views/card/batch_card/index.vue"
import BrPackage from "@/views/card/br_package/index.vue"
import PakTag from "@/views/card/pak_tag/index.vue"

let routes = [
    {
        path: "/System",
        name: "系统配置",
        component: Layout,
        icon: "el-icon-setting",
        children: [
            {
                path: "/Resource",
                name: "资源管理",
                component: Resource,
                icon: "el-icon-setting"
            },
            {
                path: "/Permission",
                name: "功能管理",
                component: Permission,
                icon: "el-icon-setting"
            },
            {
                path: "/Operation",
                name: "操作管理",
                component: Operation,
                icon: "el-icon-setting",

            },
        ]
    },
    {
        path: "/BaseData",
        name: "基本数据",
        component: Layout,
        icon: "el-icon-s-data",
        children: [{
            path: "/Geographic",
            name: "地理信息",
            component: Geographic,
            icon: "el-icon-s-data"
        }, {
            path: "/Dictionaries",
            name: "字典管理",
            component: Dictionaries,
            icon: "el-icon-s-data"
        }]
    },
    {
        path: "/CarsParts",
        name: "车型/配件管理",
        component: Layout,
        icon: "el-icon-s-home",
        children: [{
            path: "/CarBrand",
            name: "车辆品牌",
            component: CarBrand,
            icon: "el-icon-s-home"
        }, {
            path: "/CarFactory",
            name: "车辆厂家",
            component: CarFactory,
            icon: "el-icon-s-home"
        }, {
            path: "/CarCategory",
            name: "车系管理",
            component: CarCategory,
            icon: "el-icon-s-home"
        }, {
            path: "/MaintainTypeServ",
            name: "车辆保养周期",
            component: MaintainTypeServ,
            icon: "el-icon-s-home"
        }, {
            path: "/MaintainCycle",
            name: "保养类型与服务项目关系",
            component: MaintainCycle,
            icon: "el-icon-s-home"
        }, {
            path: "/PartsType",
            name: "配件目录",
            component: PartsType,
            icon: "el-icon-s-home"
        }, {
            path: "/CarParts",
            name: "配件信息",
            component: CarParts,
            icon: "el-icon-s-home"
        }]
    },
    {
        path: "/Service",
        name: "服务产品管理",
        component: Layout,
        icon: "el-icon-s-help",
        children: [{
            path: "/ServCatalog",
            name: "服务目录",
            component: ServCatalog,
            icon: "el-icon-s-help",
        }, {
            path: "/ServObject",
            name: "服务项目",
            component: ServObject,
            icon: "el-icon-s-help",
        }, {
            path: "/CheckupDirectory",
            name: "检点目录",
            component: CheckupDirectory,
            icon: "el-icon-s-help",
        }, {
            path: "/CheckupCatalog",
            name: "检点项目",
            component: CheckupCatalog,
            icon: "el-icon-s-help",
        }, {
            path: "/CheckupPkg",
            name: "检点包",
            component: CheckupPkg,
            icon: "el-icon-s-help",
        }]
    },
    {
        path: "/HR",
        name: "人力资源",
        component: Layout,
        icon: "el-icon-user-solid",
        children: [
            {
                path: "/Org",
                name: "部门管理",
                component: Org,
                icon: "el-icon-user-solid",
            },
            {
                path: "/Area",
                name: "区域管理",
                component: Area,
                icon: "el-icon-user-solid",
            },
            {
                path: "/Branches",
                name: "门店信息管理",
                component: Branches,
                icon: "el-icon-user-solid",
            },
            {
                path: "/BranchesBank",
                name: "门店银行帐户管理",
                component: BranchesBank,
                icon: "el-icon-user-solid",
            },
            {
                path: "/Employees",
                name: "员工管理",
                component: Employees,
                icon: "el-icon-user-solid",
            },
            {
                path: "/Role",
                name: "角色管理",
                component: Role,
                icon: "el-icon-user-solid",
            },
            {
                path: "/Account",
                name: "员工账号",
                component: Account,
                icon: "el-icon-user-solid",
            },
            {
                path: "/Visit",
                name: "呼叫中心回访",
                component: Visit,
                icon: "el-icon-user-solid",
            }

        ]
    },
    {
        path: "/CARD",
        name: "卡券管理",
        component: Layout,
        icon: "el-icon-bank-card",
        children: [
            {
                path: "/CardSetup",
                name: "储值卡产品定义",
                component: CardSetup,
                icon: "el-icon-bank-card"
            },
            {
                path: "/Package",
                name: "套餐产品管理",
                component: Package,
                icon: "el-icon-bank-card"
            },
            {
                path: "/CompositeProduct",
                name: "营销组合产品",
                component: CompositeProduct,
                icon: "el-icon-bank-card"
            },
            {
                path: "/Coupon",
                name: "优惠券管理",
                component: Coupon,
                icon: "el-icon-bank-card"
            },
            {
                path: "/Card",
                name: "制卡管理",
                component: Card,
                icon: "el-icon-bank-card"
            },
            {
                path: "/BrCard",
                name: "发卡管理",
                component: BrCard,
                icon: "el-icon-bank-card"
            },
            {
                path: "/BatchCard",
                name: "批量开发",
                component: BatchCard,
                icon: "el-icon-bank-card"
            },
            {
                path: "/BrPackage",
                name: "门店套餐情况",
                component: BrPackage,
                icon: "el-icon-bank-card"
            },
            {
                path: "/PakTag",
                name: "套餐标签",
                component: PakTag,
                icon: "el-icon-bank-card"
            }
        ]
    }
];

export default new Router({routes})
