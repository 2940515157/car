import Router from "vue-router"
import Layout from "../../components/layout/index.vue"

/** 车业务 */
import CustomReceive from "../../views/store/carService/custom_receive/index.vue"
import CustomInquiry from "../../views/store/carService/custom_inquiry/index.vue"
import customAppointment from "../../views/store/carService/custom_appointment/index.vue"
import MaintenanceManagement from "../../views/store/carService/maintenance_management/index.vue"
import MaintenanceDispatch from "../../views/store/carService/maintenance_dispatch/index.vue"
import MaintenanceCollection from "../../views/store/carService/maintenance_collection/index.vue"
import CheckedOrder from "../../views/store/carService/checked_order/index.vue"
/** 会员卡券 */
import MemberCardManagement from "../../views/store/member_card/member_card_management/index.vue"
import MemberCardRecharge from "../../views/store/member_card/member_card_recharge/index.vue"
import PackageSale from "../../views/store/member_card/package_sale/index.vue"
import CouponApply from "../../views/store/member_card/coupon_apply/index.vue"
import CouponSummary from "../../views/store/member_card/coupon_summary/index.vue"
import CouponDetailed from "../../views/store/member_card/coupon_detailed/index.vue"
import CardProduct from "../../views/store/member_card/card_product/index.vue"
import PackageManagement from "../../views/store/member_card/package_management/index.vue"
import CouponShelf from "../../views/store/member_card/coupon_shelf/index.vue"
import CouponPurchaseHistory from "../../views/store/member_card/coupon_purchase_history/index.vue"
import cardCollectionRecord from "../../views/store/member_card/card_collection_record/index.vue"
/** CRM */
import CustomerCarManagement from "../../views/store/CRM/customer_car_management/index.vue"
import CarInfo from "../../views/store/CRM/car_info/index.vue"
import ManagerRemind from "../../views/store/CRM/manager_remind/index.vue"
import RemindParams from "../../views/store/CRM/remind_params/index.vue"
import CustomerReturnVisit from "../../views/store/CRM/customer_return_visit/index.vue"
/** 门店仓储管理 */
import Management from "../../views/store/storage/management/index.vue"
import Initialization from "../../views/store/storage/initialization/index.vue"
import PurchaseReturn from "../../views/store/storage/purchase_return/index.vue"
import RepairPicking from "../../views/store/storage/repair_picking/index.vue"
import InventoryInfo from "../../views/store/storage/inventory_info/index.vue"
import StockInOrder from "../../views/store/storage/stock_in_order/index.vue"
import InOutStatistics from "../../views/store/storage/in_out_statistics/index.vue"
import StockOutOrder from "../../views/store/storage/stock_out_order/index.vue"
import StockTransfer from "../../views/store/storage/stock_transfer/index.vue"
import Inventory from "../../views/store/storage/inventory/index.vue"
/** 基础数据 */
import BrInfo from "../../views/store/brbasedata/br_info/index.vue"
import WorkshopInfo from "../../views/store/brbasedata/workshop_info/index.vue"
import TeamInfo from "../../views/store/brbasedata/team_info/index.vue"
import EmployeeInfo from "../../views/store/brbasedata/employee_info/index.vue"
import AccountManagement from "../../views/store/brbasedata/account_management/index.vue"
import BrServiceProduct from "../../views/store/brbasedata/br_service_product/index.vue"
import PartsCatalogue from "../../views/store/brbasedata/parts_catalogue/index.vue"
import CommodityPricing from "../../views/store/brbasedata/commodity_pricing/index.vue"
import BrCarInfo from "../../views/store/brbasedata/br_car_info/index.vue"

let routes = [
    {   path: "/Home",
        name: "首页",
        component: Layout,
        icon: "el-icon-star-on",
        children: null
    },
    {   path: "/CarService",
        name: "车业务",
        component: Layout,
        icon: "el-icon-s-custom",
        children: [
            {   path: "/CustomReceive",
                name: "客户接待",
                component: CustomReceive,
                icon: "el-icon-s-custom",
                children: null
            },
            {   path: "/CustomInquiry",
                name: "客户询价",
                component: CustomInquiry,
                icon: "el-icon-s-custom",
                children: null
            },
            {   path: "/customAppointment",
                name: "客户预约",
                component: customAppointment,
                icon: "el-icon-s-custom",
                children: null
            },
            {   path: "/MaintenanceManagement",
                name: "维修管理",
                component: MaintenanceManagement,
                icon: "el-icon-s-custom",
                children: null
            },
            {   path: "/MaintenanceDispatch",
                name: "维修派工",
                component: MaintenanceDispatch,
                icon: "el-icon-s-custom",
                children: null
            },
            {   path: "/MaintenanceCollection",
                name: "维修收款",
                component: MaintenanceCollection,
                icon: "el-icon-s-custom",
                children: null
            },
            {   path: "/CheckedOrder",
                name: "点检单",
                component: CheckedOrder,
                icon: "el-icon-s-custom",
                children: null
            },
        ]
    },
    {   path: "/MemberCard",
        name: "会员卡券",
        component: Layout,
        icon: "el-icon-bank-card",
        children: [
            {   path: "/MemberCardManagement",
                name: "会员卡管理",
                component: MemberCardManagement,
                icon: "el-icon-bank-card"
            },
            {   path: "/MemberCardRecharge",
                name: "会员卡充值",
                component: MemberCardRecharge,
                icon: "el-icon-bank-card"
            },
            {   path: "/PackageSale",
                name: "套餐销售",
                component: PackageSale,
                icon: "el-icon-bank-card"
            },
            {   path: "/CouponApply",
                name: "优惠券申请",
                component: CouponApply,
                icon: "el-icon-bank-card"
            },
            {   path: "/CouponSummary",
                name: "优惠券汇总",
                component: CouponSummary,
                icon: "el-icon-bank-card"
            },
            {   path: "/CouponDetailed",
                name: "优惠券明细",
                component: CouponDetailed,
                icon: "el-icon-bank-card"
            },
            {   path: "/CardProduct",
                name: "储值卡产品",
                component: CardProduct,
                icon: "el-icon-bank-card"
            },
            {   path: "/PackageManagement",
                name: "门店套餐管理",
                component: PackageManagement,
                icon: "el-icon-bank-card"
            },
            {   path: "/CouponShelf",
                name: "优惠券上架",
                component: CouponShelf,
                icon: "el-icon-bank-card"
            },
            {   path: "/CouponPurchaseHistory",
                name: "优惠券消费记录",
                component: CouponPurchaseHistory,
                icon: "el-icon-bank-card"
            },
            {   path: "/cardCollectionRecord",
                name: "领卡记录",
                component: cardCollectionRecord,
                icon: "el-icon-bank-card"
            },
        ]
    },
    {   path: "/CRM",
        name: "CRM车管理",
        component: Layout,
        icon: "el-icon-help",
        children: [
            {   path: "/CustomerCarManagement",
                name: "客户车辆管理",
                component: CustomerCarManagement,
                icon: "el-icon-help",
            },
            {   path: "/CarInfo",
                name: "车辆信息",
                component: CarInfo,
                icon: "el-icon-help",
            },
            {   path: "/ManagerRemind",
                name: "车管家提醒",
                component: ManagerRemind,
                icon: "el-icon-help",
            },
            {   path: "/RemindParams",
                name: "提醒参数查看",
                component: RemindParams,
                icon: "el-icon-help",
            },
            {   path: "/CustomerReturnVisit",
                name: "客户回访",
                component: CustomerReturnVisit,
                icon: "el-icon-help",
            }
        ]
    },
    {   path: "/Storage",
        name: "门店仓储管理",
        component: Layout,
        icon: "el-icon-s-home",
        children: [
            {   path: "/Management",
                name: "仓库管理",
                component: Management,
                icon: "el-icon-s-home",
            },
            {   path: "/initialization",
                name: "库存初始化",
                component: Initialization,
                icon: "el-icon-s-home",
            },
            {   path: "/PurchaseReturn",
                name: "采购退货",
                component: PurchaseReturn,
                icon: "el-icon-s-home",
            },
            {   path: "/RepairPicking",
                name: "维修领料",
                component: RepairPicking,
                icon: "el-icon-s-home",
            },
            {   path: "/InventoryInfo",
                name: "库存信息",
                component: InventoryInfo,
                icon: "el-icon-s-home",
            },
            {   path: "/StockInOrder",
                name: "入库单",
                component: StockInOrder,
                icon: "el-icon-s-home",
            },
            {   path: "/InOutStatistics",
                name: "出入库统计",
                component: InOutStatistics,
                icon: "el-icon-s-home",
            },
            {   path: "/StockOutOrder",
                name: "出库单",
                component: StockOutOrder,
                icon: "el-icon-s-home",
            },
            {   path: "/StockTransfer",
                name: "库存调拨",
                component: StockTransfer,
                icon: "el-icon-s-home",
            },
            {   path: "/Inventory",
                name: "盘点",
                component: Inventory,
                icon: "el-icon-s-home",
            },
        ]
    },
    {   path: "/BrBaseData",
        name: "门店基础数据",
        component: Layout,
        icon: "el-icon-s-data",
        children: [
            {   path: "/BrInfo",
                name: "门店信息",
                component: BrInfo,
                icon: "el-icon-s-data",
            },
            {   path: "/WorkshopInfo",
                name: "车间信息管理",
                component: WorkshopInfo,
                icon: "el-icon-s-data",
            },
            {   path: "/TeamInfo",
                name: "班组信息管理",
                component: TeamInfo,
                icon: "el-icon-s-data",
            },
            {   path: "/EmployeeInfo",
                name: "员工信息管理",
                component: EmployeeInfo,
                icon: "el-icon-s-data",
            },
            {   path: "/AccountManagement",
                name: "帐号管理",
                component: AccountManagement,
                icon: "el-icon-s-data",
            },
            {   path: "/BrServiceProduct",
                name: "服务项目管理",
                component: BrServiceProduct,
                icon: "el-icon-s-data",
            },
            {   path: "/PartsCatalogue",
                name: "配件目录",
                component: PartsCatalogue,
                icon: "el-icon-s-data",
            },
            {   path: "/CommodityPricing",
                name: "商品定价管理",
                component: CommodityPricing,
                icon: "el-icon-s-data",
            },
            {   path: "/BrCarInfo",
                name: "车型信息",
                component: BrCarInfo,
                icon: "el-icon-s-data",
            },
        ]
    }
];

export default new Router({routes})
