1. width: calc(25% - 7.5px)

Ý nghĩa:

25% đại diện cho 1/4 chiều rộng của container cha (.content-box), vì bạn muốn bố trí 4 cột ngang (100% / 4 = 25%). Điều này đảm bảo mỗi .box-description chiếm 1/4 chiều rộng tổng cộng.
- 7.5px là một phép trừ để điều chỉnh khoảng cách giữa các phần tử, liên quan đến thuộc tính justify-content: space-between của .content-box.
space-between phân bố không gian còn lại đều giữa các phần tử, tạo ra khoảng cách ngang giữa chúng. Với 4 cột, có 3 khoảng trống giữa các cột. Tổng khoảng cách ngang phụ thuộc vào chiều rộng của .content-box sau khi trừ đi padding (20px x 2 = 40px), và khoảng cách này được chia đều. Giá trị 7.5px là một ước lượng để bù đắp một nửa khoảng cách giữa các cột, đảm bảo các phần tử không chồng lấn hoặc cách quá xa.


Lý do sử dụng:

Nếu chỉ dùng 25%, các phần tử có thể không cách đều nhau do space-between tạo ra khoảng trống không đồng đều ở hai bên. Việc trừ đi 7.5px (khoảng nửa khoảng cách dự kiến) giúp điều chỉnh kích thước để các cột vừa khít hơn trong bố cục 4 cột.


Ví dụ minh họa:

Nếu .content-box có chiều rộng 1000px và padding 20px mỗi bên, chiều rộng nội dung thực tế là 940px. Với 4 cột, mỗi cột lý tưởng là 235px (940px / 4), nhưng space-between thêm khoảng trống. calc(25% - 7.5px) điều chỉnh giá trị này để phù hợp hơn với khoảng cách thực tế.



2. height: calc((850px - 40px) / 3 - 6.67px)

Ý nghĩa:

(850px - 40px): Chiều cao tổng của .content-box là 850px, trừ đi padding trên và dưới (20px + 20px = 40px), ta được chiều cao nội dung thực tế là 810px. Đây là không gian có thể phân bổ cho các hàng.
/ 3: Vì bạn muốn có 3 hàng, chiều cao mỗi hàng lý tưởng là 810px / 3 = 270px.
- 6.67px: Đây là điều chỉnh để bù đắp cho margin-bottom: 10px và padding (10px) giữa các hàng. Với 3 hàng, có 2 khoảng trống dọc (margin-bottom giữa các hàng), và giá trị 6.67px là một ước lượng để giảm chiều cao mỗi phần tử sao cho tổng chiều cao không vượt quá 810px. (2 x 10px margin = 20px, chia đều cho 3 hàng ≈ 6.67px mỗi hàng để điều chỉnh).


Lý do sử dụng:

Chiều cao cố định 850px của .content-box cần được chia đều cho 3 hàng, nhưng các yếu tố như padding và margin-bottom làm tăng tổng chiều cao nếu không điều chỉnh. calc() giúp tính toán chiều cao mỗi .box-description sao cho vừa khít với 3 hàng, tránh tràn hoặc khoảng trống thừa.


Ví dụ minh họa:

Với chiều cao nội dung 810px, mỗi hàng lý tưởng là 270px. Tuy nhiên, margin-bottom: 10px giữa các hàng (tổng 20px cho 2 khoảng) và padding 10px trên/dưới mỗi .box-description làm tổng chiều cao tăng. Giảm đi 6.67px (ước lượng) giúp chiều cao mỗi phần tử ≈ 263.33px, phù hợp với bố cục 3 hàng.