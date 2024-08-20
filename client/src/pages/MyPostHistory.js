import React, { useEffect, useState, useRef, useCallback } from 'react';
import './MyPostHistory.css';

import AppContainer from '../components/AppContainer';
import Header from '../components/Header';


const AppContainerComponent = () => {
    return (
        <AppContainer />
    );
};

const HeaderComponent = () => {
    return (
        <Header />
    );
};

const MBTIHistoryTitle = () => {
    return (
        <div className="post-history-title" >
            <img src="post-img.png" alt="history-item"/>
            내 MBTI 기록
        </div>
    );
};

const Historyrecenttext = () => {
    return (
        <div className="history-recenttext">
            최신 작성 순
        </div>
    );
};

const HistorySection = () => {
    const [historyData, setHistoryData] = useState([]);
    const [page, setPage] = useState(1);
    const observer = useRef();

    const fetchHistoryData = useCallback(async () => {
        // 데이터를 가져오는 API 호출
        const response = await fetch(`/api/my-posts?page=${page}`);
        const data = await response.json();
        setHistoryData(prevData => [...prevData, ...data]);
    }, [page]);

    useEffect(() => {
        fetchHistoryData();
    }, [fetchHistoryData]);

    const lastHistoryElementRef = useCallback(node => {
        if (observer.current) observer.current.disconnect();
        observer.current = new IntersectionObserver(entries => {
            if (entries[0].isIntersecting) {
                setPage(prevPage => prevPage + 1);
            }
        });
        if (node) observer.current.observe(node);
    }, []);

    const dummyData = [
        { title: '첫 번째 게시글', date: '2023-01-01 12:00' },
        { title: '두 번째 게시글', date: '2023-01-02 13:00' },
        { title: '세 번째 게시글', date: '2023-01-03 14:00' },
        { title: '네 번째 게시글', date: '2023-01-04 15:00' },
        { title: '다섯 번째 게시글', date: '2023-01-05 16:00' },
        { title: '여섯 번째 게시글', date: '2023-01-06 17:00' },
        { title: '일곱 번째 게시글', date: '2023-01-06 17:00' },
        { title: '여덟 번째 게시글', date: '2023-01-06 17:00' },
    ];

    const displayData = historyData.length > 0 ? historyData : dummyData;

    return (
        <div className="history-section">
            {displayData.map((item, index) => (
                <div
                    className={`history-section-content ${index % 2 === 0 ? 'white-background' : 'gray-background'}`}
                    key={index}
                    ref={index === displayData.length - 1 ? lastHistoryElementRef : null}
                >
                    <div className="history-content-text">{item.title}</div>
                    <div className="history-content-date">{item.date}</div>
                </div>
            ))}
        </div>
    );
};


const MyPostHistory = () => {
    return (
      <div className="app">
        <AppContainerComponent />
        <HeaderComponent />
        <MBTIHistoryTitle />
        <Historyrecenttext />
        <HistorySection />
      </div>
    );
  };

export default MyPostHistory;