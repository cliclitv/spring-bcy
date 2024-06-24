import { getAv, getAvatar } from '../../util/avatar'
import './avatar.css'

export default function Avatar(props) {
    return <div className="avatar">
        <img src={getAvatar(props.email)} alt="" />{
            props.name && <p>{props.name}</p>
        }
    </div>
}