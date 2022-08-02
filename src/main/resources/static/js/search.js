new Vue({
    el: '#searchDiv',
    data() {
        return {
            stores: [],
            state: '',
            timeout:  null
        };
    },
    methods: {
        querySearchAsync(queryString, cb) {
                if (queryString === '') {
                    cb(this.stores);
                } else {
                    axios.get("/brand/like/" + queryString).then(
                        res => {
                            try {
                                if (res.data.code === 0) {
                                    clearTimeout(this.timeout);
                                    let data = [];
                                    res.data.data.forEach(i => {
                                        data.push({'value': i.storeName});
                                    })
                                    this.stores = data;
                                    this.timeout = setTimeout(() => {
                                        cb(data);
                                    }, 1000);
                                } else {
                                    cb([]);
                                }
                            } catch (err) {
                                cb([]);
                            }
                        }
                    );
                }
        },
        handleSelect(item) {
            window.open('/brand_detail?brandName=' + encodeURIComponent(item.value), '_self');
        }
    },
    mounted() {
        axios.get('/brand/like/a').then(res => {
            if(res.data.code === 0) {
                let data = [];
                res.data.data.forEach(i => {
                    data.push({'value': i.storeName});
                })
                this.stores = data;
            } else {
                this.stores = [];
            }
        })
    }
})
