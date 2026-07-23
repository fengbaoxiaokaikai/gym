<template>
  <div style="padding: 24px">
    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 16px">
      <h2 style="margin: 0">添加优惠券</h2>
      <el-button @click="router.push('/coupon/selCoupon')">返回</el-button>
    </div>

    <el-card style="max-width: 860px">
      <el-form label-width="140px">
        <el-form-item label="券码">
          <el-input v-model="form.couponCode" />
        </el-form-item>
        <el-form-item label="优惠券名称">
          <el-input v-model="form.couponName" />
        </el-form-item>
        <el-form-item label="优惠券类型">
          <el-select v-model="form.couponType" style="width: 100%">
            <el-option label="满减" value="满减" />
            <el-option label="折扣" value="折扣" />
            <el-option label="赠课" value="赠课" />
          </el-select>
        </el-form-item>
        <el-form-item label="优惠值">
          <el-input-number v-model="form.discountAmount" :min="0" :precision="2" style="width: 100%" />
        </el-form-item>
        <el-form-item label="门槛金额">
          <el-input-number v-model="form.minimumAmount" :min="0" :precision="2" style="width: 100%" />
        </el-form-item>
        <el-form-item label="开始时间">
          <el-date-picker v-model="form.startTime" type="date" value-format="YYYY-MM-DD" style="width: 100%" />
        </el-form-item>
        <el-form-item label="结束时间">
          <el-date-picker v-model="form.endTime" type="date" value-format="YYYY-MM-DD" style="width: 100%" />
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="form.couponStatus" style="width: 100%">
            <el-option label="启用" value="启用" />
            <el-option label="停用" value="停用" />
          </el-select>
        </el-form-item>
        <el-form-item label="库存">
          <el-input-number v-model="form.couponStock" :min="0" style="width: 100%" />
        </el-form-item>
        <el-form-item label="备注信息">
          <el-input v-model="form.couponMessage" type="textarea" />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submit">添加</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { reactive } from 'vue'
import { useRouter } from 'vue-router'
import { postForm } from '../api/client'

const router = useRouter()
const form = reactive({
  couponCode: '',
  couponName: '',
  couponType: '满减',
  discountAmount: 0,
  minimumAmount: 0,
  startTime: '',
  endTime: '',
  couponStatus: '启用',
  couponStock: 0,
  couponMessage: ''
})

function validate() {
  if (!form.couponCode) return alert('请输入券码！')
  if (!form.couponName) return alert('请输入优惠券名称！')
  if (!form.couponType) return alert('请选择优惠券类型！')
  if (!form.startTime) return alert('请选择开始时间！')
  if (!form.endTime) return alert('请选择结束时间！')
  if (!form.couponStatus) return alert('请选择状态！')
  return true
}

async function submit() {
  if (!validate()) return

  await postForm('/api/coupon/addCoupon', {
    couponCode: form.couponCode,
    couponName: form.couponName,
    couponType: form.couponType,
    discountAmount: Number(form.discountAmount),
    minimumAmount: Number(form.minimumAmount),
    startTime: form.startTime,
    endTime: form.endTime,
    couponStatus: form.couponStatus,
    couponStock: Number(form.couponStock),
    couponMessage: form.couponMessage
  })

  router.push('/coupon/selCoupon')
}
</script>
