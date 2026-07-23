<template>
  <div style="padding: 24px">
    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 16px">
      <h2 style="margin: 0">优惠券管理</h2>
      <el-button type="primary" @click="router.push('/coupon/toAddCoupon')">添加优惠券</el-button>
    </div>

    <el-table :data="couponList" style="width: 100%">
      <el-table-column prop="couponId" label="编号" width="90" />
      <el-table-column prop="couponCode" label="券码" width="130" />
      <el-table-column prop="couponName" label="名称" />
      <el-table-column prop="couponType" label="类型" width="100" />
      <el-table-column prop="discountAmount" label="优惠值" width="110" />
      <el-table-column prop="minimumAmount" label="门槛金额" width="110" />
      <el-table-column prop="startTime" label="开始时间" width="130" />
      <el-table-column prop="endTime" label="结束时间" width="130" />
      <el-table-column label="状态" width="100">
        <template #default="scope">
          <el-tag :type="scope.row.couponStatus === '启用' ? 'success' : 'info'">
            {{ scope.row.couponStatus }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="couponStock" label="库存" width="90" />
      <el-table-column prop="couponMessage" label="备注信息" />
      <el-table-column label="操作" width="180">
        <template #default="scope">
          <el-button size="small" type="info" @click="edit(scope.row.couponId)">编辑</el-button>
          <el-button size="small" type="danger" style="margin-left: 8px" @click="del(scope.row.couponId)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <div v-if="!couponList.length" style="color: #666; margin-top: 16px">暂无优惠券数据</div>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { useRouter } from 'vue-router'
import api, { postForm } from '../api/client'

const router = useRouter()
const couponList = ref([])

async function load() {
  const resp = await api.get('/api/coupon/selCoupon')
  couponList.value = resp.data?.couponList || []
}

function edit(couponId) {
  router.push({ path: '/coupon/toUpdateCoupon', query: { couponId } })
}

async function del(couponId) {
  if (!confirm('确定要删除该优惠券吗？')) return
  await postForm('/api/coupon/delCoupon', { couponId })
  await load()
}

onMounted(() => {
  load().catch(() => {})
})
</script>
