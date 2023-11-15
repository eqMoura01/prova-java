import './styles.css'
import {Link} from "react-router-dom";

import packageJson from '../../../package.json';

function Header(props) {
    return (
        <div className="header">
            <div className='logo-container'>
                <Link to='/'>
                    <img
                        src={require('../../assets/citel-software-logo.png')}
                        style={{ width: 150, padding: 10 }}
                        alt={'Logo'} />
                </Link>
            </div>
            <div className='version-container'>
                {packageJson.version}
            </div>
        </div>
    );
}
export default Header;