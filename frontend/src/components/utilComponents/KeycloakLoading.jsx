import { useState, useEffect } from 'react';
import LoadingIcon from "./LoadingIcon";
import UserService from '../../config/UserService';

function KeycloakLoading({children}) {

    const [error, setError] = useState('');
    const [state, setState] = useState('');

    useEffect(() => {
        setState('loading');
        UserService.doLogin()
        .then(() => {
            setState('success');
            console.log('Keycloak redirect');
        })
        .catch((err) => {
            console.error('Error:', err);
            setState('error');
            setError(err);
        });
    }, []);

    if (state === 'error')
        return (
            <h1>
                {error.toString()}
            </h1>
        );
    return (
        <div>
            <div>
                {state === 'loading' ? (
                    <LoadingIcon/>
                ) : (
                    children
                )}
            </div>
        </div>
    );
}
  
export default KeycloakLoading;