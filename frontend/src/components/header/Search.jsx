import {useState} from 'react';
import { useNavigate } from "react-router-dom";
import { useSearchParams } from "react-router-dom";
import '../../styles/Header.css'
import SearchIcon from "../../assets/SearchIcon";

export default function Search() {

    const [searchParams, setSearchParams] = useSearchParams();
    const navigate = useNavigate();
    const [query, setQuery] = useState(searchParams.get("q") || "");

    const navigation = (e) => {
        e.preventDefault();
        setSearchParams({ q: query });
        navigate(`/products/search?q=${query}`);
    }

    return (
            <form className='search-form' onSubmit={(e) => navigation(e)}>
                <input 
                    className="search-input" 
                    type='text' 
                    placeholder='Search' 
                    autoComplete='off'
                    value={query} 
                    onChange={(e) => setQuery(e.target.value)}
                />
                <button type='submit'>
                    <SearchIcon/>
                </button>
            </form>
    );
}