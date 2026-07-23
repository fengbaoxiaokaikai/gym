<template>
  <div style="padding: 24px">
    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 16px">
      <h2 style="margin: 0">编辑优惠券</h2>
      <el-button @click="router.push('/coupon/selCoupon')">返回</el-button>
    </div>

    <el-card style="max-width: 860px" v-if="coupon">
      <el-form label-width="140px">
        <el-form-item label="编号">
          <el-input v-model="coupon.couponId" disabled />
        </el-form-item>
        <el-form-item label="券码">
          <el-input v-model="coupon.couponCode" />
        </el-form-item>
        <el-form-item label="优惠券名称">
          <el-input v-model="coupon.couponName" />
        </el-form-item>
        <el-form-item label="优惠券类型">
          <el-select v-model="coupon.couponType" style="width: 100%">
            <el-option label="满减" value="满减" />
            <el-option label="折扣" value="折扣" />
            <el-option label="赠课" value="赠课" />
          </el-select>
        </el-form-item>
        <el-form-item label="优惠值">
          <el-input-number v-model="coupon.discountAmount" :min="0" :precision="2" style="width: 100%" />
        </el-form-item>
        <el-form-item label="门槛金额">
          <el-input-number v-model="coupon.minimumAmount" :min="0" :precision="2" style="width: 100%" />
        </el-form-item>
        <el-form-item label="开始时间">
          <el-date-picker v-model="coupon.startTime" type="date" value-format="YYYY-MM-DD" style="width: 100%" />
        </el-form-item>
        <el-form-item label="结束时间">
          <el-date-picker v-model="coupon.endTime" type="date" value-format="YYYY-MM-DD" style="width: 100%" />
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="coupon.couponStatus" style="width: 100%">
            <el-option label="启用" value="启用" />
            <el-option label="停用" value="停用" />
          </el-select>
        </el-form-item>
        <el-form-item label="库存">
          <el-input-number v-model="coupon.couponStock" :min="0" style="width: 100%" />
        </el-form-item>
        <el-form-item label="备注信息">
          <el-input v-model="coupon.couponMessage" type="textarea" />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submit">确认修改</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <el-card v-else>加载中...</el-card>
  </div>
</template>

<script setup>
import { onMounted, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import api, { postForm } from '../api/client'

const route = useRoute()
const router = useRouter()
const coupon = ref(null)

async function load() {
  const couponId = Number(route.query.couponId || 0)
  const resp = await api.get('/api/coupon/toUpdateCoupon', { params: { couponId } })
  const list = resp.data?.couponList || []
  coupon.value = list[0] || null
}

function validate() {
  if (!coupon.value) return false
  if (!coupon.value.couponCode) return alert('请输入券码！')
  if (!coupon.value.couponName) return alert('请输入优惠券名称！')
  if (!coupon.value.couponType) return alert('请选择优惠券类型！')
  if (!coupon.value.startTime) return alert('请选择开始时间！')
  if (!coupon.value.endTime) return alert('请选择结束时间！')
  if (!coupon.value.couponStatus) return alert('请选择状态！')
  return true
}

async function submit() {
  if (!validate()) return

  await postForm('/api/coupon/updateCoupon', {
    couponId: coupon.value.couponId,
    couponCode: coupon.value.couponCode,
    couponName: coupon.value.couponName,
    couponType: coupon.value.couponType,
    discountAmount: Number(coupon.value.discountAmount),
    minimumAmount: Number(coupon.value.minimumAmount),
    startTime: coupon.value.startTime,
    endTime: coupon.value.endTime,
    couponStatus: coupon.value.couponStatus,
    couponStock: Number(coupon.value.couponStock),
    couponMessage: coupon.value.couponMessage
  })

  router.push('/coupon/selCoupon')
}

onMounted(() => {
  load().catch(() => {})
})
</script>
