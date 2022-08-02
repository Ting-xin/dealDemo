new Vue({
    el: '#pageDiv',

    methods: {
        handleSizeChange(val) {
            this.pageSize = val;
            this.submit();
        },
        handleCurrentChange(val) {
            this.page = val;
            this.submit();
        },
        handleSortChange(val) {
            this.radio = val;
            this.submit();
        },
        submit() {
            let temp = "/brand?page=" + this.page + '&size=' + this.pageSize+'&orderType=' + this.radio;
            console.log('open new page: ', temp);
            // axios.get("/brand?page=" + this.page + '&size=' + this.pageSize);
            window.open(temp, '_self');
        }
    },
    data() {
        return {
            page: 1,
            pageSize: 10,
            total:208882,
            radio: 'Popularity'
        };
    },
    mounted() {
        let temp = location.href.split('?')[1];
        this.page = 1;
        this.pageSize = 10;
        let page, size, radio;
        if(temp && temp != '') {
            let param = temp.split('&');
            page = +param[0].split('=')[1];
            size = +param[1].split('=')[1];
            radio = param[2].split('=')[1];
        }
        if(page) {
            this.page = page;
        }
        if(size) {
            this.pageSize = size;
        }

        let set = new Set(['Popularity', 'Rating', 'Alphabetical']);
        if(radio && set.has(radio)) {
            this.radio = radio;
        } else {
            this.radio = 'Popularity';
        }
    }
})
