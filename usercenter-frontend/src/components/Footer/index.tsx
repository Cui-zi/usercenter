import { GithubOutlined } from '@ant-design/icons';
import { DefaultFooter } from '@ant-design/pro-components';
import {MY_GITHUB_LINK} from "@/constants";


const Footer: React.FC = () => {
  const defaultMessage = '子裕出品';
  const currentYear = new Date().getFullYear();
  return (
    <DefaultFooter
      copyright={`${currentYear} ${defaultMessage}`}
      links={[
        // {
        //   key: 'Ant Design Pro',
        //   title: 'Ant Design Pro',
        //   href: 'https://pro.ant.design',
        //   blankTarget: true,
        // },

        {
          key: 'github',
          title: <><GithubOutlined /> 子裕 GitHub</>,
          href: MY_GITHUB_LINK,
          blankTarget: true,
        },

        // {
        //   key: 'Ant Design',
        //   title: 'Ant Design',
        //   href: 'https://ant.design',
        //   blankTarget: true,
        // },
      ]}
    />
  );
};
export default Footer;
