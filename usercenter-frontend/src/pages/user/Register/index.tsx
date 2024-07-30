import Footer from '@/components/Footer';
import {register} from '@/services/ant-design-pro/api';

import {
  LockOutlined,
  UserOutlined,
} from '@ant-design/icons';

import {
  LoginForm,
  ProFormText,
} from '@ant-design/pro-components';

import { message, Tabs } from 'antd';
import React, { useState } from 'react';
import { history } from 'umi';
import styles from './index.less';
import {SYSTEM_LOGO} from "@/constants";


const Register: React.FC = () => {

  const [type, setType] = useState<string>('account');

  //表单提交
  const handleSubmit = async (values: API.RegisterParams) => {

    const {userPassword, checkPassword, identityCode} = values;

    //校验
    if(userPassword != checkPassword){
      message.error('输入的两次密码不一致');
      return;
    }
    if(identityCode.length != 18){
      message.error('身份证号格式错误');
      return;
    }

    try {
      // 注册
      const id = await register(values);
      if (id) {
        const defaultLoginSuccessMessage = '注册成功！';
        message.success(defaultLoginSuccessMessage);

        /** 此方法会跳转到 redirect 参数所在的位置 */
        if (!history) return;
        const { query } = history.location;
        history.push({
          pathname: '/umi/login',
          query,
        });
        return;
      }
    } catch (error: any) {
      const defaultLoginFailureMessage = '注册失败，请重试！';
      message.error(defaultLoginFailureMessage);
    }
  };



  return (
    <div className={styles.container}>
      <div className={styles.content}>

        <LoginForm

          submitter={{
            searchConfig:{
              submitText: '注册'
            }
          }}

          logo={<img alt="logo" src={SYSTEM_LOGO} />}
          title="子裕的用户管理中心"
          subTitle={'这是一个高质量的管理系统'}
          initialValues={{
            autoLogin: true,
          }}

          // actions={[
          //   '其他注册方式 :',
          //   <AlipayCircleOutlined key="AlipayCircleOutlined" className={styles.icon} />,
          //   <TaobaoCircleOutlined key="TaobaoCircleOutlined" className={styles.icon} />,
          //   <WeiboCircleOutlined key="WeiboCircleOutlined" className={styles.icon} />,
          // ]}

          onFinish={async (values) => {
            await handleSubmit(values as API.RegisterParams);
          }}
        >

          <Tabs activeKey={type} onChange={setType}>
            <Tabs.TabPane key="account" tab={'账号注册'} />

            {/*<Tabs.TabPane key="mobile" tab={'手机号注册'} />*/}
          </Tabs>

          {type === 'account' && (
            <>
              <ProFormText
                name="userAccount"
                fieldProps={{
                  size: 'large',
                  prefix: <UserOutlined className={styles.prefixIcon} />,
                }}
                placeholder={'请输入账号'}
                rules={[
                  {
                    required: true,
                    message: '账号是必填项！',
                  },
                ]}
              />

              <ProFormText.Password
                name="checkPassword"
                fieldProps={{
                  size: 'large',
                  prefix: <LockOutlined className={styles.prefixIcon} />,
                }}
                placeholder={'请输入密码'}
                rules={[
                  {
                    required: true,
                    message: '密码是必填项！',
                  },

                  {
                    min: 8,
                    type: 'string',
                    message: '长度不能小于8！',
                  },
                ]}
              />

              <ProFormText.Password
                name="userPassword"
                fieldProps={{
                  size: 'large',
                  prefix: <LockOutlined className={styles.prefixIcon} />,
                }}
                placeholder={'请再次输入密码'}
                rules={[
                  {
                    required: true,
                    message: '确认密码是必填项！',
                  },

                  {
                    min: 8,
                    type: 'string',
                    message: '长度不能小于8！',
                  },
                ]}
              />

              <ProFormText
                name="identityCode"
                fieldProps={{
                  size: 'large',
                  prefix: <UserOutlined className={styles.prefixIcon} />,
                }}
                placeholder={'请输入18位身份证号'}
                rules={[
                  {
                    required: true,
                    message: '身份证号是必填项！',
                  },
                ]}
              />
            </>
          )}
        </LoginForm>
      </div>
      <Footer />
    </div>
  );
};
export default Register;
