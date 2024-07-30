import { Settings as LayoutSettings } from '@ant-design/pro-components';

const Settings: LayoutSettings & {
  pwa?: boolean;
  logo?: string;
} = {
  navTheme: 'light',
  // 拂晓蓝
  primaryColor: '#1890ff',
  layout: 'mix',
  contentWidth: 'Fluid',
  fixedHeader: false,
  fixSiderbar: true,
  colorWeak: false,
  title: '子裕的用户管理系统',
  pwa: false,
  logo: 'https://himg.bdimg.com/sys/portrait/item/public.1.82d0a505.hd1F2_fxqGv1nPtIozr6cQ?tt=1721959488681',
  iconfontUrl: '',
};
//'https://gw.alipayobjects.com/zos/rmsportal/KDpgvguMpGfqaHPjicRK.svg',
export default Settings;
